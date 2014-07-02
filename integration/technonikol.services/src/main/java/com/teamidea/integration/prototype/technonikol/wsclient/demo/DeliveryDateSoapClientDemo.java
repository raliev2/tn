package com.teamidea.integration.prototype.technonikol.wsclient.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.technonikol.ws.stocks.DeliveryDateQueryOut;

import com.teamidea.integration.prototype.technonikol.wsclient.DeliveryDateSoapClient;

public class DeliveryDateSoapClientDemo
{
	
	private AbstractApplicationContext ctx;

	public DeliveryDateSoapClientDemo(AbstractApplicationContext ctx){
		this.ctx = ctx;
	}

	public static void main(String[] args) throws IOException {
		DeliveryDateSoapClientDemo demo = new DeliveryDateSoapClientDemo(new ClassPathXmlApplicationContext("technonikolServicesBeans.xml"));
		DeliveryDateQueryOut port = (DeliveryDateQueryOut) demo.ctx.getBean("deliveryDate");
		DeliveryDateSoapClient client = new DeliveryDateSoapClient(port);
		
		System.out.println(String.format("DeliveryDateService demo app started\n" +
				"Type help for list of commands\n"));

		String command = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			try{				
				String sargs[] = reader.readLine().split("\\s+");
				command = sargs[0];
				
				Options options = new Options();
				options.addOption("e", true, "ekn");
				options.addOption("c", true, "count");
				options.addOption("d", true, "post date");

				PosixParser clParser = new PosixParser();
				CommandLine line = clParser.parse(options, sargs);
				switch (command) {
				case "help":
					System.out.println(String.format("Available commands:\n" +
							" * deliveryDateQueryOut\n" +
							" * exit\n"));
					
					HelpFormatter formatter = new HelpFormatter(); 
					formatter.printHelp( " ", options);
					break;
				case "deliveryDateQueryOut":
					String ekn = line.getOptionValue("e");
					String count = line.getOptionValue("c");
					String datePost = line.getOptionValue("d");
					
					if(!StringUtils.isEmpty(ekn)
							&& !StringUtils.isEmpty(count)
							&& !StringUtils.isEmpty(datePost)){
						try{
							client.deliveryDateQueryOut(ekn, count, datePost);	
						}
						catch(Exception ex){
							System.out.println("Error while trying to get delivery date");
							ex.printStackTrace();
						}
					}
				
					break;
				case "exit":
					System.out.println("Bye");
					break;
				default:
					System.out.println("Unknown command");
				}
								
			} catch (ParseException clie){
				System.out.println("ERROR: incorrect format.");
				clie.printStackTrace();
			}
			
		} while (!"exit".equals(command.toLowerCase()));
		
	}

}
