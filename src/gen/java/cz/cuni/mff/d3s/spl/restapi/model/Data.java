package cz.cuni.mff.d3s.spl.restapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Data
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-05-23T16:23:56.084+02:00")
public class Data   {
  @JsonProperty("units")
  private String units = null;

  @JsonProperty("data")
  private List<List<Double>> data = new ArrayList<List<Double>>();

  public Data units(String units) {
    this.units = units;
    return this;
  }

   /**
   * Units of values (all have the same) as reported by JMH
   * @return units
  **/
  @ApiModelProperty(example = "ns/op", value = "Units of values (all have the same) as reported by JMH")
  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public Data data(List<List<Double>> data) {
    this.data = data;
    return this;
  }

  public Data addDataItem(List<Double> dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * Values separated by JMH forks (different JVM instances)
   * @return data
  **/
  @ApiModelProperty(value = "Values separated by JMH forks (different JVM instances)")
  public List<List<Double>> getData() {
    return data;
  }

  public void setData(List<List<Double>> data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Data data = (Data) o;
    return Objects.equals(this.units, data.units) &&
        Objects.equals(this.data, data.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(units, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Data {\n");
    
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

