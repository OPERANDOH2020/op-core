package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.BDAJob;
import java.util.ArrayList;
import java.util.List;



/**
 * OSP job list
 **/

@ApiModel(description = "OSP job list")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public class OSPJobs   {
  
  private List<BDAJob> jobs = new ArrayList<BDAJob>();

  /**
   **/
  public OSPJobs jobs(List<BDAJob> jobs) {
    this.jobs = jobs;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("jobs")
  public List<BDAJob> getJobs() {
    return jobs;
  }
  public void setJobs(List<BDAJob> jobs) {
    this.jobs = jobs;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPJobs oSPJobs = (OSPJobs) o;
    return Objects.equals(jobs, oSPJobs.jobs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPJobs {\n");
    
    sb.append("    jobs: ").append(toIndentedString(jobs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

