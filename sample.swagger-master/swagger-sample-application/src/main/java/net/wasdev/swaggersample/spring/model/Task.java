package net.wasdev.swaggersample.spring.model;

import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiModelProperty;


//------------------------------------------------------------------------------------------------
// DTO for HTTP session (Task).
// * If you introduce lombok, you can abbreviate every setter/getter.
//------------------------------------------------------------------------------------------------
@RequestMapping({"/tasks"})
public class Task {

	@ApiModelProperty(required = true, example = "Make an app for Demo")
	private String description;

	@ApiModelProperty(required = true, example = "Created")
	private String status;

	@ApiModelProperty(required = true, dataType = "date", example = "01/15/2016")
	private String deadline;


	/* setter/getter */
	@ApiModelProperty(required = true, example = "Make an app for Demo")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@ApiModelProperty(required = true, example = "Created")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@ApiModelProperty(required = true, dataType = "date", example = "01/15/2016")
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}