package net.wasdev.swaggersample.spring.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


//------------------------------------------------------------------------------------------------
// DTO for HTTP session (TaskWithID).
// * If you introduce lombok, you can abbreviate every setter/getter.
//------------------------------------------------------------------------------------------------
@ApiModel(parent = Task.class)
public class TaskWithID extends Task {

	@ApiModelProperty(readOnly = true, required = true)
	private int id;

	public TaskWithID(Task task, int id) {
		this.id = id;
		this.setDeadline(task.getDeadline());
		this.setDescription(task.getDescription());
		this.setStatus(task.getStatus());
	}


	/* setter/getter */
	public int getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
