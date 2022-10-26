package com.kontarook.sequenceofnumbers.controller;

import com.kontarook.sequenceofnumbers.service.impl.NumbersServiceImpl;
import com.kontarook.sequenceofnumbers.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.NoSuchFileException;

@RestController
@Api(value = "Searches for a file on the disk along the passed path and performs the requested operation on it")
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class NumbersController {

    private final NumbersServiceImpl numbersServiceImpl;

    @Autowired
    public NumbersController(NumbersServiceImpl operationsOnNumbers) {
        this.numbersServiceImpl = operationsOnNumbers;
    }

    @ApiOperation("Get the maximum number in the file")
    @GetMapping(value = "/get_max_value")
    public ResponseEntity<Object> getMaxValue() throws NullPointerException {

        JSONObject response = new JSONObject()
                .put("max_value", numbersServiceImpl.getMaxValue());
        return ResponseEntity.ok(response.toString());
    }

    @ApiOperation("Get the minimum number in the file")
    @GetMapping("/get_min_value")
    public ResponseEntity<Object> getMinValue() throws NullPointerException {

        JSONObject response = new JSONObject()
                .put("min_value", numbersServiceImpl.getMinValue());
        return ResponseEntity.ok(response.toString());
    }

    @ApiOperation("Get median")
    @GetMapping("/get_median")
    public ResponseEntity<Object> getMedian() throws NullPointerException {

        JSONObject response = new JSONObject().put("median", numbersServiceImpl.getMedianNumber());
        return ResponseEntity.ok(response.toString());
    }

    @ApiOperation("Get sequence of consecutive numbers in ascending and descending orders")
    @GetMapping("/get_longest_sequence/{order}")
    public ResponseEntity<Object> getLongestSequence(@ApiParam(value = "order", allowableValues = "asc, desc")
                                                     @PathVariable(name = "order") String order)
                                                            throws NullPointerException {

        JSONObject response;
        // DESCENDING / ASCENDING
        if (order.equals("desc")) {
            response = new JSONObject()
                    .put("longest-descending-sequence", numbersServiceImpl.longestSequenceDesc());
        } else {
            response = new JSONObject()
                    .put("longest-ascending-sequence", numbersServiceImpl.longestSequenceAsc());
        }
        return ResponseEntity.ok(response.toString());
    }

    @ApiOperation("Get average")
    @GetMapping("/get_average")
    public ResponseEntity<Object> getAverage() throws NullPointerException {

        JSONObject response = new JSONObject().put("average", numbersServiceImpl.getAverage());
        return ResponseEntity.ok(response.toString());
    }

    @ApiOperation("Upload file")
    @PostMapping(value = "upload_file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> uploadFile(@RequestPart(required = false) MultipartFile file)
            throws NoSuchFileException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        long size = file.getSize();

        String filePath = FileUploadUtil.saveFile(fileName, file);
        numbersServiceImpl.setFilePath(filePath);
        numbersServiceImpl.parseNumbersFromFile();

        JSONObject response = new JSONObject().put("File", fileName).put("size", size + " bytes");

        return ResponseEntity.ok(response.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<Object> handleException(NullPointerException e) {
        JSONObject response = new JSONObject()
                .put("Error", "File wasn't found");
        return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchFileException.class)
    private ResponseEntity<Object> handleException(NoSuchFileException e) {
        JSONObject response = new JSONObject()
                .put("Error", "File wasn't uploaded");
        return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
    }
}
