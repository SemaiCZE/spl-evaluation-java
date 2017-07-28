package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Version
 */

public class Version   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("timestamp")
  private Long timestamp = null;

  public Version id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique version identifier, usually
   * @return id
  **/
  @ApiModelProperty(example = "1501159669-7649a1c363f58f732b0503130ea93f0ef0719e15", required = true, value = "Unique version identifier, usually")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Version timestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Version timestamp, usually creation time of measured commit. Used format is Unix timestamp (number of seconds elapsed since January 1, 1970). If the timestamp is unknown, 0 is returned.
   * @return timestamp
  **/
  @ApiModelProperty(example = "1501159669", required = true, value = "Version timestamp, usually creation time of measured commit. Used format is Unix timestamp (number of seconds elapsed since January 1, 1970). If the timestamp is unknown, 0 is returned.")
  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Version version = (Version) o;
    return Objects.equals(this.id, version.id) &&
        Objects.equals(this.timestamp, version.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Version {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

