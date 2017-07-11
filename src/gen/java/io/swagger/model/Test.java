package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public class Test extends HashMap<String, String>  {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("score")
  private Double score = null;

  @JsonProperty("scoreUnit")
  private String scoreUnit = null;

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
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, score, scoreUnit, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Test {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    scoreUnit: ").append(toIndentedString(scoreUnit)).append("\n");
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

