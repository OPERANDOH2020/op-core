package io.swagger.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.deidentifier.arx.ARXAnonymizer;
import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.AttributeType;
import org.deidentifier.arx.AttributeType.Hierarchy;
import org.deidentifier.arx.AttributeType.Hierarchy.DefaultHierarchy;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.DataSource;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.criteria.KAnonymity;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.PersonaldataApiService;
import io.swagger.model.AEDataType;
import io.swagger.model.SearchRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class PersonaldataApiServiceImpl extends PersonaldataApiService {

	private Connection connect = null;
	static Logger mainLogger = null;
	static {
		mainLogger = Logger.getLogger("eu.operando.core.ae.server");
	}

	@Override
	public Response getPersonalData(SearchRequest searchRequest, SecurityContext securityContext)
			throws NotFoundException {
		mainLogger.logp(Level.INFO, "getPersonalData", "main", "Testing logging in tomcat docker ... ");
		String strAnnonymizedData = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, String> dataTypeMap = (HashMap<String, String>) new ObjectMapper()
					.readValue(searchRequest.getDataTypeMap(), HashMap.class);
			int kAnonymity = searchRequest.getKAnonymity().intValue();
			HashMap<String, Object> attributeTypeMap = (HashMap<String, Object>) new ObjectMapper()
					.readValue(searchRequest.getAttributeTypeMap(), HashMap.class);		
			strAnnonymizedData = arxAnonymizationPersonalData(searchRequest.getQueryName(), searchRequest.getQuery(),
					dataTypeMap, attributeTypeMap, kAnonymity);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, strAnnonymizedData)).build();
	}

	/** Returns the annonymized data given the query to be executes and some other parameters
	 * @param queryName
	 * @param query
	 * @param dataTypeMap
	 * @param attributeTypeMap
	 * @param kAnonymity
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	private String arxAnonymizationPersonalData(String queryName, String query, HashMap<String, String> dataTypeMap,
			HashMap<String, Object> attributeTypeMap, int kAnonymity)
			throws ClassNotFoundException, SQLException, IOException {

		String strAnnonymizedData = "";
		Properties props;
		props = loadDbProperties();
		Class.forName(props.getProperty("jdbc.driverClassName"));
		connect = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),
				props.getProperty("jdbc.password"));

		Statement st = connect.createStatement();

		st.execute("DROP VIEW IF EXISTS " + queryName + ";");

		String sentence = " Create VIEW " + queryName + " As " + query;

		st.executeUpdate(sentence);

		DataSource source = DataSource.createJDBCSource(props.getProperty("jdbc.url"),
				props.getProperty("jdbc.username"), props.getProperty("jdbc.password"), queryName);

		Set<String> dataTypeKeySet = dataTypeMap.keySet();
		Set<Entry<String, String>> entrySet = dataTypeMap.entrySet();
		Iterator<Entry<String, String>> dataTypeIterator = entrySet.iterator();
		
		String dataTypeValue = null;
		String dataTypeKey = null;
		
		while (dataTypeIterator.hasNext()) {
			Entry<String, String> next = dataTypeIterator.next();			
			dataTypeKey = next.getKey();
			dataTypeValue = next.getValue();
			switch (dataTypeValue) {
			case AEDataType.STRING:				
				source.addColumn(dataTypeKey, DataType.STRING);
				break;
			case AEDataType.INTEGER:				
				source.addColumn(dataTypeKey, DataType.INTEGER);
				break;
			case AEDataType.DECIMAL:				
				source.addColumn(dataTypeKey, DataType.DECIMAL);
				break;
			case AEDataType.DATE:				
				source.addColumn(dataTypeKey, DataType.DATE);
				break;
			default:
				break;
			}
		}

		mainLogger.logp(Level.INFO, "getPersonalData", "main", "Database source success  ... ");

		// This will load the MySQL driver, each DB has its own driver
		Class.forName(props.getProperty("jdbc.driverClassName"));

		mainLogger.logp(Level.INFO, "getPersonalData", "main", "Default Hierarchies success  ... ");

		// Create data object
		Data data = Data.create(source);
		Set<String> attributeTypeKeySet = attributeTypeMap.keySet();
		Iterator<String> attributeTypeIterator = attributeTypeKeySet.iterator();

		String attributeName = "";
		while (attributeTypeIterator.hasNext()) {			
			attributeName = attributeTypeIterator.next();
			if (attributeTypeMap.get(attributeName).getClass().getName().equalsIgnoreCase("java.lang.String")) {
				String attributeValue = (String) attributeTypeMap.get(attributeName);
				switch (attributeValue) {
				case "IDENTIFYING_ATTRIBUTE":
					data.getDefinition().setAttributeType(attributeName, AttributeType.IDENTIFYING_ATTRIBUTE);
					break;
				case "SENSITIVE_ATTRIBUTE":
					data.getDefinition().setAttributeType(attributeName, AttributeType.SENSITIVE_ATTRIBUTE);
					break;
				case "INSENSITIVE_ATTRIBUTE":
					data.getDefinition().setAttributeType(attributeName, AttributeType.INSENSITIVE_ATTRIBUTE);
					break;
				case "QUASI_IDENTIFYING_ATTRIBUTE":
					data.getDefinition().setAttributeType(attributeName, AttributeType.QUASI_IDENTIFYING_ATTRIBUTE);
					break;
				default:
					break;
				}
			}
			// in this case the value is of class ArrayList<ArrayList<String>>
			else {
				DefaultHierarchy hierarchy = Hierarchy.create();
				@SuppressWarnings("unchecked")
				ArrayList<ArrayList<String>> attributeValue = (ArrayList<ArrayList<String>>) attributeTypeMap
						.get(attributeName);
				Iterator<ArrayList<String>> attributeValuesIterator = attributeValue.iterator();
				ArrayList<String> value = null;
				String attValue = "";
				String strAdd = "";
				while (attributeValuesIterator.hasNext()) {
					value = (ArrayList<String>) attributeValuesIterator.next();
					if (value.size() > 0) {
						Iterator<String> valueIterator = value.iterator();
						if (valueIterator.hasNext()) {
							strAdd = valueIterator.next();
						}

						while (valueIterator.hasNext()) {
							attValue = valueIterator.next();
							strAdd += "," + attValue;

						}
						hierarchy.add(strAdd);
					} // end if
				}
				data.getDefinition().setAttributeType(attributeName, hierarchy);

			}
		}

		ARXAnonymizer anonymizer = new ARXAnonymizer();
		ARXConfiguration config = ARXConfiguration.create();
		config.addCriterion(new KAnonymity(kAnonymity));

		ARXResult result = anonymizer.anonymize(data, config);
		st.close();
		connect.close();

		Iterator<String[]> transformed = result.getOutput().iterator();
		int i = 0;
		StringBuffer strBufferAnnonymizedData = new StringBuffer("");
		while (transformed.hasNext()) {
			System.out.print("   ");
			i++;
			String outputmessage = Arrays.toString(transformed.next());
			strBufferAnnonymizedData.append(outputmessage);			
			mainLogger.logp(Level.INFO, "getPersonalData", "main", outputmessage);
		}
		strAnnonymizedData = strBufferAnnonymizedData.toString();
		System.out.println(strAnnonymizedData);
		return (strAnnonymizedData);
	}

	/**
	 * Prints a given data handle.
	 *
	 * @param handle
	 */
	protected static void print(DataHandle handle) {
		final Iterator<String[]> itHandle = handle.iterator();
		print(itHandle);
	}

	/**
	 * Prints a given iterator.
	 *
	 * @param iterator
	 */
	protected static void print(Iterator<String[]> iterator) {
		while (iterator.hasNext()) {
			System.out.print("   ");
			String outputmessage = Arrays.toString(iterator.next());
			System.out.println(outputmessage);
			mainLogger.logp(Level.INFO, "getPersonalData", "main", "PRINT FUNCTION: " + outputmessage);
		}
	}

	private Properties loadDbProperties() {
		Properties props;
		props = new Properties();

		InputStream fis = null;
		try {
			fis = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
			props.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return props;
	}

}
