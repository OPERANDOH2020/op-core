/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Robbie Anderson
//      Created Date :          2016-09-02
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////


/*
 * @author ra16 <ra16@it-innovation.soton.ac.uk
 */
package eu.operando.core.cpcu.main;

/**
 * The Class DataHolder. This class is used within the Sliding door processing as a way of packaging the Data name and Data rank together.
 * This was chosen so compareTo could be implemented and utilised in an attempt to speed up comparisons.
 * It is also used in handling Rank name and data. (In service Questionnaire)
 */
public class DataHolder implements Comparable<DataHolder>{

	/** The data. */
	private String data;
	
	/** The rank. */
	private Integer rank;
	
	/**
	 * Instantiates a new data holder.
	 *
	 * @param data the data
	 * @param rank the rank
	 */
	public DataHolder(String data, Integer rank){
		this.data = data;
		this.rank = rank;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return data;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		DataHolder holder = (DataHolder) obj;
		return (getData().equalsIgnoreCase(holder.getData()));
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	return 1;
    }

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DataHolder o) {
		return getRank()-o.getRank();
	}
}
