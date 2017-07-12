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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-12T12:01:17.872+02:00")
public class Test   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("score")
  private Double score = null;

  @JsonProperty("scoreUnit")
  private String scoreUnit = null;

  @JsonProperty("optional")
  private Map<String, String> optional = new HashMap<String, String>();

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

  public Test score(Double score) {
    this.score = score;
    return this;
  }

   /**
   * Test score, usually mean or median
   * @return score
  **/
  @ApiModelProperty(example = "1.0998768334366429E8", value = "Test score, usually mean or median")
  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public Test scoreUnit(String scoreUnit) {
    this.scoreUnit = scoreUnit;
    return this;
  }

   /**
   * Unit of the score above
   * @return scoreUnit
  **/
  @ApiModelProperty(example = "ops/s", value = "Unit of the score above")
  public String getScoreUnit() {
    return scoreUnit;
  }

  public void setScoreUnit(String scoreUnit) {
    this.scoreUnit = scoreUnit;
  }

  public Test optional(Map<String, String> optional) {
    this.optional = optional;
    return this;
  }

  public Test putOptionalItem(String key, String optionalItem) {
    this.optional.put(key, optionalItem);
    return this;
  }

   /**
   * Optional test metadata (execution parameters, etc.)
   * @return optional
  **/
  @ApiModelProperty(value = "Optional test metadata (execution parameters, etc.)")
  public Map<String, String> getOptional() {
    return optional;
  }

  public void setOptional(Map<String, String> optional) {
    this.optional = optional;
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
        Objects.equals(this.score, test.score) &&
        Objects.equals(this.scoreUnit, test.scoreUnit) &&
        Objects.equals(this.optional, test.optional);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, score, scoreUnit, optional);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Test {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    scoreUnit: ").append(toIndentedString(scoreUnit)).append("\n");
    sb.append("    optional: ").append(toIndentedString(optional)).append("\n");
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

