package net.wasdev.swaggersample.spring.dba;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//------------------------------------------------------------------------------------------------
// DTO for table.
// * If you introduce lombok, you can abbreviate every setter/getter.
//------------------------------------------------------------------------------------------------
@Entity
@Table(name="task_with_id")
public class TableDefinition {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="description")
	private String description;

	@Column(name="status")
	private String status;

	@Column(name="deadline")
	private String deadline;


	/* setter/getter */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return trimRightSpace(description);
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return trimRightSpace(status);
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeadline() {
		return trimRightSpace(deadline);
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}


	/* Format data from DB to Swagger UI */
	// Trim right concurrency spaces
	public static String trimRightSpace(String target){
		if (target == null){
			return "";
		}
		return target.replaceAll(" +$", "");
	}
}
