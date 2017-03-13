package net.wasdev.swaggersample.spring;
import net.wasdev.swaggersample.spring.model.Task;
import net.wasdev.swaggersample.spring.model.TaskWithID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//------------------------------------------------------------------------------------------------
// RestController converts the response to JSON/XML automatically
//------------------------------------------------------------------------------------------------
@RestController
public class ResourceController implements ResourceApi {

	private Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();
	private volatile int currentId = 0;

	/* "GET" all tasks */
	@Override
	public ResponseEntity<List<TaskWithID>> getTasks()
	{
		List<TaskWithID> result = new ArrayList<TaskWithID>(tasks.size());
		for (Entry<Integer, Task> taskEntry : tasks.entrySet()) {
			result.add(new TaskWithID(taskEntry.getValue(), taskEntry.getKey()));
		}
		return new ResponseEntity<List<TaskWithID>>(result, HttpStatus.OK);
	}


	/* "POST" a task with new id */
	@Override
	public ResponseEntity<String> createTasks(
			@ApiParam(value = "", required=true ) @RequestBody Task body)
	{
		tasks.put(currentId, body);
		return new ResponseEntity<String>("{\"id\":" + currentId++ + "}", HttpStatus.CREATED);
	}


	/* "GET" a task specified by id */
	@Override
	public ResponseEntity<Task> getTask(
			@ApiParam(value = "", required=true ) @PathVariable("id") Integer id)
	{
		Task task = tasks.get(id);
		if (task != null) {
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}


	/* "PUT" a task specified by id */
	@Override
	public ResponseEntity<Integer> updateTask(
			@ApiParam(value = "", required=true ) @PathVariable("id") Integer id,
			@ApiParam(value = "", required=true ) @RequestBody Task body)
	{
		if (tasks.get(id) != null) {
			tasks.put(id, body);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}


	/* "DELETE" a task specified by id */
	@Override
	public ResponseEntity<Integer> deleteTask(
			@ApiParam(value = "", required=true ) @PathVariable("id") Integer id)
	{
		if (tasks.get(id) != null) {
			tasks.remove(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
