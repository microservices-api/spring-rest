/**
* (C) Copyright IBM Corporation 2016.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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

	@ApiModelProperty(required = true, dataType = "date", example = "01/15/16")
	private String deadline;

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

	@ApiModelProperty(required = true, dataType = "date", example = "01/15/16")
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}
