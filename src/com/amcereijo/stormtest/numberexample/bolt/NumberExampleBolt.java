package com.amcereijo.stormtest.numberexample.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class NumberExampleBolt implements IRichBolt{

	private static final long serialVersionUID = -4086604975824808430L;

	 private OutputCollector collector;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		int number = input.getInteger(0);
		log("number:"+input.getIntegerByField("number"));
		if( isPrime(number) ){
			logComputed(String.format("Number %s is Prime!!", number));
		}
		collector.ack(input);
	}
	
	private boolean isPrime( int n ) {
       if( n == 1 || n == 2 || n == 3 ){
           return true;
       }
       // Is n an even number?
       if( n % 2 == 0 ){
           return false;
       }
       //if not, then just check the odds
       for( int i=3; i*i<=n; i+=2 ){
           if( n % i == 0){
               return false;
           }
       }
       return true;
   }

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare( new Fields( "number" ) );
	}
	
	private void log(String message){
		System.out.println(String.format("Bolt - %s", message));
	}
	
	private void logComputed(String message){
		System.out.println(String.format("Computed Bold - %s", message));
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}
}
