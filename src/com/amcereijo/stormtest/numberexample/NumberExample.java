package com.amcereijo.stormtest.numberexample;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

import com.amcereijo.stormtest.numberexample.bolt.NumberExampleBolt;
import com.amcereijo.stormtest.numberexample.spout.NumberExampleSpout;

public class NumberExample {

	public static void main(String[] args) {
		 System.out.println( "Start Storm example!" );
	        LocalCluster cluster;
	        try{
	        TopologyBuilder builder = new TopologyBuilder();
	        	builder.setSpout("spout", new NumberExampleSpout());
	        	builder.setBolt("primeBolt", new NumberExampleBolt()).shuffleGrouping("spout");
	        
	        Config config = new Config();
	        
	        cluster = new LocalCluster();
	        String clusterName = "test";
			cluster.submitTopology(clusterName, config, builder.createTopology());
	        Utils.sleep(10000);
	        cluster.killTopology(clusterName);
	        cluster.shutdown();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	        	System.out.println( "End Storm example!" );	
	        }
	}
}
