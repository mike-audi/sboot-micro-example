/*
 * Copyright (c) 2018 Blustream Corporation.
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to
 * deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom
 * the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sboot.micro.example.mvc.resource;


import com.sboot.micro.example.mvc.dao.ExampleModel;
import com.sboot.micro.example.mvc.service.ExampleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Original Author(s): Mike Audi
 * Purpose: Example rest controller
 */

@RestController
@RequestMapping(value="/example", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Example", description = "Example API resource", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ExampleService exampleService;

    public ExampleController(@Autowired ExampleService exampleService){
        this.exampleService = exampleService;
    }

    @ApiOperation(value = "Get a exampleModel", response = ExampleModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @RequestMapping(value = "/{modelId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExampleModel getModel(
            @ApiParam(value = "Unique model id (UUID)", required = true)
            @PathVariable(value = "modelId") String modelId,
            @ApiParam(value = "Example query parameter", required = true, defaultValue = "0")
            @RequestParam(required = false, defaultValue = "0") Integer offset) {
        logger.trace("GET /example/" + modelId);

        return new ExampleModel();
    }

    @ApiOperation(value = "Put a model", response = ExampleModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ExampleModel.class),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{modelId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExampleModel putModel(
            @ApiParam(value = "Unique exampleModel id (UUID)", required = true)
            @PathVariable(value = "modelId") String modelId,
            @ApiParam(value = "The exampleModel to create", required = true)
            @RequestBody ExampleModel model) {
        logger.trace("PUT /example/" + model.getId());
        exampleService.makeModel();
        return model;
    }
}
