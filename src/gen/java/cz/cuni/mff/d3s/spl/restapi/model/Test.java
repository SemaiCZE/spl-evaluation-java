package cz.cuni.mff.d3s.spl.restapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * Test
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-05-23T16:23:56.084+02:00")
public class Test   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("benchmark")
  private String benchmark = null;

  /**
   * Mode of benchmarking
   */
  public enum ModeEnum {
    THRPT("thrpt"),
    
    SAMPLE("sample"),
    
    SS("ss"),
    
    AVGT("avgt");

    private String value;

    ModeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ModeEnum fromValue(String text) {
      for (ModeEnum b : ModeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("mode")
  private ModeEnum mode = null;

  @JsonProperty("threads")
  private Integer threads = null;

  @JsonProperty("forks")
  private Integer forks = null;

  @JsonProperty("warmupIterations")
  private Integer warmupIterations = null;

  @JsonProperty("warmupTime")
  private String warmupTime = null;

  @JsonProperty("warmupBatchSize")
  private Integer warmupBatchSize = null;

  @JsonProperty("measurementIterations")
  private Integer measurementIterations = null;

  @JsonProperty("measurementTime")
  private String measurementTime = null;

  @JsonProperty("measurementBatchSize")
  private Integer measurementBatchSize = null;

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
  @ApiModelProperty(value = "Unique identifier of this test case")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Test benchmark(String benchmark) {
    this.benchmark = benchmark;
    return this;
  }

   /**
   * Full name of test method as seen by Java
   * @return benchmark
  **/
  @ApiModelProperty(example = "cz.stdin.ps.SampleTest.testMethod", value = "Full name of test method as seen by Java")
  public String getBenchmark() {
    return benchmark;
  }

  public void setBenchmark(String benchmark) {
    this.benchmark = benchmark;
  }

  public Test mode(ModeEnum mode) {
    this.mode = mode;
    return this;
  }

   /**
   * Mode of benchmarking
   * @return mode
  **/
  @ApiModelProperty(example = "thrpt", value = "Mode of benchmarking")
  public ModeEnum getMode() {
    return mode;
  }

  public void setMode(ModeEnum mode) {
    this.mode = mode;
  }

  public Test threads(Integer threads) {
    this.threads = threads;
    return this;
  }

   /**
   * Number of used threads
   * @return threads
  **/
  @ApiModelProperty(example = "1", value = "Number of used threads")
  public Integer getThreads() {
    return threads;
  }

  public void setThreads(Integer threads) {
    this.threads = threads;
  }

  public Test forks(Integer forks) {
    this.forks = forks;
    return this;
  }

   /**
   * Number of forks (sequentialy executed separate JVM instances)
   * @return forks
  **/
  @ApiModelProperty(example = "1", value = "Number of forks (sequentialy executed separate JVM instances)")
  public Integer getForks() {
    return forks;
  }

  public void setForks(Integer forks) {
    this.forks = forks;
  }

  public Test warmupIterations(Integer warmupIterations) {
    this.warmupIterations = warmupIterations;
    return this;
  }

   /**
   * Number of iterations which do not count to the results
   * @return warmupIterations
  **/
  @ApiModelProperty(example = "10", value = "Number of iterations which do not count to the results")
  public Integer getWarmupIterations() {
    return warmupIterations;
  }

  public void setWarmupIterations(Integer warmupIterations) {
    this.warmupIterations = warmupIterations;
  }

  public Test warmupTime(String warmupTime) {
    this.warmupTime = warmupTime;
    return this;
  }

   /**
   * Time which do not count to the results
   * @return warmupTime
  **/
  @ApiModelProperty(example = "5 s", value = "Time which do not count to the results")
  public String getWarmupTime() {
    return warmupTime;
  }

  public void setWarmupTime(String warmupTime) {
    this.warmupTime = warmupTime;
  }

  public Test warmupBatchSize(Integer warmupBatchSize) {
    this.warmupBatchSize = warmupBatchSize;
    return this;
  }

   /**
   * Number of iterations for one value
   * @return warmupBatchSize
  **/
  @ApiModelProperty(example = "1", value = "Number of iterations for one value")
  public Integer getWarmupBatchSize() {
    return warmupBatchSize;
  }

  public void setWarmupBatchSize(Integer warmupBatchSize) {
    this.warmupBatchSize = warmupBatchSize;
  }

  public Test measurementIterations(Integer measurementIterations) {
    this.measurementIterations = measurementIterations;
    return this;
  }

   /**
   * Number of iterations which do count to the results (after warmup)
   * @return measurementIterations
  **/
  @ApiModelProperty(example = "5", value = "Number of iterations which do count to the results (after warmup)")
  public Integer getMeasurementIterations() {
    return measurementIterations;
  }

  public void setMeasurementIterations(Integer measurementIterations) {
    this.measurementIterations = measurementIterations;
  }

  public Test measurementTime(String measurementTime) {
    this.measurementTime = measurementTime;
    return this;
  }

   /**
   * Time which do count to the results (after warmup)
   * @return measurementTime
  **/
  @ApiModelProperty(example = "3 s", value = "Time which do count to the results (after warmup)")
  public String getMeasurementTime() {
    return measurementTime;
  }

  public void setMeasurementTime(String measurementTime) {
    this.measurementTime = measurementTime;
  }

  public Test measurementBatchSize(Integer measurementBatchSize) {
    this.measurementBatchSize = measurementBatchSize;
    return this;
  }

   /**
   * Number of iterations for one value
   * @return measurementBatchSize
  **/
  @ApiModelProperty(example = "1", value = "Number of iterations for one value")
  public Integer getMeasurementBatchSize() {
    return measurementBatchSize;
  }

  public void setMeasurementBatchSize(Integer measurementBatchSize) {
    this.measurementBatchSize = measurementBatchSize;
  }

  public Test score(Double score) {
    this.score = score;
    return this;
  }

   /**
   * Test score produced by JMH
   * @return score
  **/
  @ApiModelProperty(example = "1.0998768334366429E8", value = "Test score produced by JMH")
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
        Objects.equals(this.benchmark, test.benchmark) &&
        Objects.equals(this.mode, test.mode) &&
        Objects.equals(this.threads, test.threads) &&
        Objects.equals(this.forks, test.forks) &&
        Objects.equals(this.warmupIterations, test.warmupIterations) &&
        Objects.equals(this.warmupTime, test.warmupTime) &&
        Objects.equals(this.warmupBatchSize, test.warmupBatchSize) &&
        Objects.equals(this.measurementIterations, test.measurementIterations) &&
        Objects.equals(this.measurementTime, test.measurementTime) &&
        Objects.equals(this.measurementBatchSize, test.measurementBatchSize) &&
        Objects.equals(this.score, test.score) &&
        Objects.equals(this.scoreUnit, test.scoreUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, benchmark, mode, threads, forks, warmupIterations, warmupTime, warmupBatchSize, measurementIterations, measurementTime, measurementBatchSize, score, scoreUnit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Test {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    benchmark: ").append(toIndentedString(benchmark)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    threads: ").append(toIndentedString(threads)).append("\n");
    sb.append("    forks: ").append(toIndentedString(forks)).append("\n");
    sb.append("    warmupIterations: ").append(toIndentedString(warmupIterations)).append("\n");
    sb.append("    warmupTime: ").append(toIndentedString(warmupTime)).append("\n");
    sb.append("    warmupBatchSize: ").append(toIndentedString(warmupBatchSize)).append("\n");
    sb.append("    measurementIterations: ").append(toIndentedString(measurementIterations)).append("\n");
    sb.append("    measurementTime: ").append(toIndentedString(measurementTime)).append("\n");
    sb.append("    measurementBatchSize: ").append(toIndentedString(measurementBatchSize)).append("\n");
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

