package net.wasdev.swaggersample.spring;
import net.wasdev.swaggersample.spring.dba.TableDefinition;
import net.wasdev.swaggersample.spring.dba.TaskWithIDRepository;
import net.wasdev.swaggersample.spring.model.Task;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//------------------------------------------------------------------------------------------------
// RestController converts the response to JSON/XML automatically
//------------------------------------------------------------------------------------------------
@RestController
public class ResourceController implements ResourceApi {

	@Autowired
	TaskWithIDRepository taskWithIDRepository;


	/* "GET" all tasks in Table */
	@Override
	public ResponseEntity<List<TableDefinition>> getTasks()
	{
		// Sort is essential at the viewpoint of usability. "findAll" doesn't sort with "id". 
		List<TableDefinition> result = taskWithIDRepository.findAllByOrderByIdAsc();

		return new ResponseEntity<List<TableDefinition>>(result, HttpStatus.OK);
	}


	/* "POST" a task with new id */
	// @Transactinal abbreviate every update/insert detail handling process (such as begin -> commit-> rollback...)
	@Override
	@Transactional
	public ResponseEntity<String> createTasks(
			@ApiParam(value = "", required = true ) @RequestBody Task body)
	{
		int updateId;
		// To avoid no record causes NullPointerException 
		updateId = (taskWithIDRepository.findMaxId() == null)? 0 : taskWithIDRepository.findMaxId() + 1;

		String description = body.getDescription();
		String status = body.getStatus();
		String deadline = body.getDeadline();
		deadline = checkDateFormat(deadline);

		taskWithIDRepository.create(updateId, description, status, deadline);

		return new ResponseEntity<String>("{\"id\":" + updateId + "}", HttpStatus.CREATED);
	}


    /* "GET" a task specified by id */
	@Override
	public ResponseEntity<TableDefinition> getTask(
			@ApiParam(value = "", required = true ) @PathVariable("id") Integer id)
	{
		TableDefinition task = taskWithIDRepository.findByid(id);
		if (task != null) {
			return new ResponseEntity<TableDefinition>(task, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}


	/* "PUT" a task specified by id */
	@Override
	@Transactional
	public ResponseEntity<Integer> updateTask(
			@ApiParam(value = "", required=true ) @PathVariable("id") Integer id,
			@ApiParam(value = "", required=true ) @RequestBody Task body)
	{
		if (taskWithIDRepository.findByid(id) != null) {

			String description = body.getDescription();
			String status = body.getStatus();
			String deadline = body.getDeadline();
			deadline = checkDateFormat(deadline);

			taskWithIDRepository.save(id,description,status,deadline);

			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}


	/* "DELETE" a task specified by id */
	@Override
	public ResponseEntity<Integer> deleteTask(
			@ApiParam(value = "", required = true ) @PathVariable("id") Integer id)
	{
		if (taskWithIDRepository.findByid(id) != null) {
			taskWithIDRepository.delete(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}


	/* Format input String to "yyyy/MM/DD" Date-look */
	public static String checkDateFormat(String dateStr){

		String strFormat = "MM/dd/yyyy";
		DateFormat dateModified = new SimpleDateFormat(strFormat);

		// String -> Date.
		// * To avoid parseException, use ParsePosition 
		ParsePosition pos = new ParsePosition(0);
		Date date = dateModified.parse(dateStr, pos);
		if( pos.getErrorIndex() >=0 ){
			return "[WARNING] " + dateStr;
		}

		// Date -> String. 
		SimpleDateFormat finalDateStr = new SimpleDateFormat(strFormat);
		finalDateStr.applyPattern(strFormat);
		return finalDateStr.format(date);
	}
}
