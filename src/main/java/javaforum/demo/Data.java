package javaforum.demo;


public record Data(
	String applicationName, 
	String environmentName, 
	String database, 
	String stringValue) {
}