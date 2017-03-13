package net.wasdev.swaggersample.spring;
import net.wasdev.swaggersample.spring.model.Task;
import net.wasdev.swaggersample.spring.model.TaskWithID;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//------------------------------------------------------------------------------------------------
// Controller of every Swagger menu (GET, POST, DELETE, PUT...)
//------------------------------------------------------------------------------------------------
@Scope("/tasks")
@Api(value = "Tasks API")
@SwaggerDefinition(tags = { @Tag(name = "Tasks API", description = "Spring Based API") })
@Controller
public interface ResourceApi {

	/* "GET" all tasks */
	@ApiOperation(value = "Retrieve all tasks", response = TaskWithID.class, nickname = "getTasks", responseContainer = "List", tags = { "Tasks API", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "successful operation") })
	@RequestMapping(value = "/swagger-sample/demo/tasks",
		produces = { "application/json" }, 
		method = RequestMethod.GET)
	ResponseEntity<List<TaskWithID>> getTasks();


	/* "POST" a task with new id */
	@ApiOperation(value = "Create a task", response = String.class, nickname = "createTask", tags = { "Tasks API", })
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Task created") })
	@RequestMapping(value = "/swagger-sample/demo/tasks",
		produces = { "application/json" }, 
		consumes = { "application/json" },
		method = RequestMethod.POST)
	ResponseEntity<String> createTasks(@ApiParam(value = "", required=true ) @RequestBody Task body);


	/* "GET" a task specified by id */
	@ApiOperation(value = "Get a task with ID", response = Task.class, nickname = "getTask", tags = { "Tasks API", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Task retrieved"),
		@ApiResponse(code = 404, message = "Task not found") })
	@RequestMapping(value = "/swagger-sample/demo/tasks/{id}",
		produces = { "application/json" }, 
		method = RequestMethod.GET)
	ResponseEntity<Task> getTask(@ApiParam(value = "", required=true ) @PathVariable("id") Integer id);


	/* "PUT" a task specified by id */
	@ApiOperation(value = "Update a task with ID", response = Integer.class, nickname = "updateTask", tags = { "Tasks API", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Task updated"),
		@ApiResponse(code = 404, message = "Task not found") })
	@RequestMapping(value = "/swagger-sample/demo/tasks/{id}",
		produces = { "text/plain" }, 
		consumes = { "application/json" },
		method = RequestMethod.PUT)
	ResponseEntity<Integer> updateTask(@ApiParam(value = "", required=true ) @PathVariable("id") Integer id,
		@ApiParam(value = "" ,required=true ) @RequestBody Task body);


	/* "DELETE" a task specified by id */
	@ApiOperation(value = "Delete a task with ID", response = Integer.class, nickname = "deleteTask", tags = { "Tasks API", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Task deleted"),
		@ApiResponse(code = 404, message = "Task not found") })
	@RequestMapping(value = "/swagger-sample/demo/tasks/{id}",
		produces = { "text/plain" },
		method = RequestMethod.DELETE)
	ResponseEntity<Integer> deleteTask(@ApiParam(value = "", required=true ) @PathVariable("id") Integer id);
}
