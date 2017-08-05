package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test
 */

public class Test   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("metadata")
  private Map<String, String> metadata = new HashMap<String, String>();

  public Test id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier of this test case
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier of this test case")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Test name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of test case, usually full method name
   * @return name
  **/
  @ApiModelProperty(example = "cz.stdin.ps.SampleTest.testMethod", required = true, value = "Name of test case, usually full method name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Test metadata(Map<String, String> metadata) {
    this.metadata = metadata;
    return this;
  }

  public Test putMetadataItem(String key, String metadataItem) {
    this.metadata.put(key, metadataItem);
    return this;
  }

   /**
   * Optional test metadata (execution parameters, etc.)
   * @return metadata
  **/
  @ApiModelProperty(value = "Optional test metadata (execution parameters, etc.)")
  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Test test = (Test) o;
    return Objects.equals(this.id, test.id) &&
        Objects.equals(this.name, test.name) &&
        Objects.equals(this.metadata, test.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Test {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

