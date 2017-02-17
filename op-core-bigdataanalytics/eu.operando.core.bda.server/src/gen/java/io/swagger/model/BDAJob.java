package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-06T21:13:34.473Z")
public class BDAJob   {
  
  private Integer jobId = null;
  private String jobName = null;
  private Boolean subscribed = null;
  private String status = null;
  private String downloadLink = null;

  /**
   **/
  public BDAJob jobId(Integer jobId) {
    this.jobId = jobId;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("jobId")
  public Integer getJobId() {
    return jobId;
  }
  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  /**
   **/
  public BDAJob jobName(String jobName) {
    this.jobName = jobName;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("jobName")
  public String getJobName() {
    return jobName;
  }
  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  /**
   **/
  public BDAJob subscribed(Boolean subscribed) {
    this.subscribed = subscribed;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("subscribed")
  public Boolean getSubscribed() {
    return subscribed;
  }
  public void setSubscribed(Boolean subscribed) {
    this.subscribed = subscribed;
  }

  /**
   **/
  public BDAJob status(String status) {
    this.status = status;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   **/
  public BDAJob downloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("downloadLink")
  public String getDownloadLink() {
    return downloadLink;
  }
  public void setDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BDAJob bDAJob = (BDAJob) o;
    return Objects.equals(jobId, bDAJob.jobId) &&
        Objects.equals(jobName, bDAJob.jobName) &&
        Objects.equals(subscribed, bDAJob.subscribed) &&
        Objects.equals(status, bDAJob.status) &&
        Objects.equals(downloadLink, bDAJob.downloadLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, jobName, subscribed, status, downloadLink);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BDAJob {\n");
    
    sb.append("    jobId: ").append(toIndentedString(jobId)).append("\n");
    sb.append("    jobName: ").append(toIndentedString(jobName)).append("\n");
    sb.append("    subscribed: ").append(toIndentedString(subscribed)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    downloadLink: ").append(toIndentedString(downloadLink)).append("\n");
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

