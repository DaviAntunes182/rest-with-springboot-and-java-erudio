package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converters.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {
	
	private final AtomicLong counter = new AtomicLong();
	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value="/math/sum/{numOne}/{numTwo}", method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numOne")
			String numOne, 
			@PathVariable(value = "numTwo")
			String numTwo) throws Exception {
		//Tratando
		if(!NumberConverter.isNumeric(numOne) || !NumberConverter.isNumeric(numTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		return math.sum(NumberConverter.convertToDouble(numOne), NumberConverter.convertToDouble(numTwo));
	}
	
	@RequestMapping(value="/math/sub/{numOne}/{numTwo}", method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numOne")
			String numOne, 
			@PathVariable(value = "numTwo")
			String numTwo) throws Exception {
		if(!NumberConverter.isNumeric(numOne)|| !NumberConverter.isNumeric(numTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.sub(NumberConverter.convertToDouble(numOne), NumberConverter.convertToDouble(numTwo));
	}
	
	@RequestMapping(value="/math/mult/{numOne}/{numTwo}", method=RequestMethod.GET)
	public Double mult(
			@PathVariable(value = "numOne")
			String numOne, 
			@PathVariable(value = "numTwo")
			String numTwo) throws Exception {
		if(!NumberConverter.isNumeric(numOne)|| !NumberConverter.isNumeric(numTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.mult(NumberConverter.convertToDouble(numOne), NumberConverter.convertToDouble(numTwo));
		
	}

	@RequestMapping(value="/math/div/{numOne}/{numTwo}", method=RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numOne")
			String numOne, 
			@PathVariable(value = "numTwo")
			String numTwo) throws Exception {
		if(!NumberConverter.isNumeric(numOne)|| !NumberConverter.isNumeric(numTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.div(NumberConverter.convertToDouble(numOne), NumberConverter.convertToDouble(numTwo));
	}
	
	@RequestMapping(value="/math/avg/{numOne}/{numTwo}", method=RequestMethod.GET)
	public Double avg(
			@PathVariable(value = "numOne")
			String numOne, 
			@PathVariable(value = "numTwo")
			String numTwo) throws Exception {
		if(!NumberConverter.isNumeric(numOne) || !NumberConverter.isNumeric(numTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.avg(NumberConverter.convertToDouble(numOne), NumberConverter.convertToDouble(numTwo));
	}

	@RequestMapping(value="/math/sqrt/{num}", method=RequestMethod.GET)
	public Double sqrt(
			@PathVariable(value = "num")
			String num) throws Exception {
		if(!NumberConverter.isNumeric(num)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.sqrt(NumberConverter.convertToDouble(num));
	}
	
}
