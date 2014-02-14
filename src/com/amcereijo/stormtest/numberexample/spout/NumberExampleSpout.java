package com.amcereijo.stormtest.numberexample.spout;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class NumberExampleSpout implements IRichSpout{

	private static final long serialVersionUID = 226293784682316869L;

	private SpoutOutputCollector collector;
    
    private static int currentNumber = 1;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
		collector.emit(new Values(new Integer(currentNumber++)));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("number"));
	}
	
	@Override
	public void ack(Object msgId) {
		log("ack with object "+msgId);
		
	}

	
	private void log(String message){
		System.out.println(String.format("Spout - %s", message));
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fail(Object msgId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDistributed() {
		// TODO Auto-generated method stub
		return false;
	}
}
