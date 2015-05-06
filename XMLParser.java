//Provides everything you need to work with the DOM
//Document, Element, Node, NodeList, Text, Exceptions, etc.

import org.w3c.dom.*;

import javax.xml.xpath.*;
import javax.xml.parsers.*;

import java.io.IOException;

//SAX Simple API for XML



import java.util.ArrayList;

import org.xml.sax.SAXException;

public class XMLParser {
	private Document doc = null;

	public XMLParser() {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();

		// Provides support for XML namespaces if needed

		domFactory.setNamespaceAware(true);

		// Turns xml into a DOM tree

		DocumentBuilder builder;

		try {

			// parses the file supplied

			builder = domFactory.newDocumentBuilder();

			doc = builder.parse("./save.xml");
		}

		catch (SAXException e) {

			e.printStackTrace();
		}

		catch (IOException e) {

			e.printStackTrace();
		}

		catch (ParserConfigurationException e) {

			e.printStackTrace();
		}
	}
	public int numWars() {
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				Object result = null;
				int num = 0;

				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}

				// Outputs the results of the query

				NodeList nodes = (NodeList) result;
				
				num = Integer.parseInt(nodes.item(0).getNodeValue());
				// Cycles through the results
				return num;
	}
	public String canUpdate() {
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				Object result = null;
				int num = 0;

				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//setup//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}

				// Outputs the results of the query

				NodeList nodes = (NodeList) result;
				
				
				// Cycles through the results
				return nodes.item(0).getNodeValue();
	}
	public war loadWar(int num, String version) {
		if (!version.equals("1.6")) {
			return load14War(num, version);
		} else {
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				war wars = new war();
				Object result = null;
				int attackNum = 1;

				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/Participants/member/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}

				// Outputs the results of the query

				NodeList nodes = (NodeList) result;

				// Cycles through the results
				for (int i = 0; i < nodes.getLength(); i = i + 4) {
					Player play = new Player(nodes.item(i).getNodeValue());
					play.setAttackU(Integer.parseInt(nodes.item(i + 1).getNodeValue()));
					play.setAttackW(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					play.setStars(Integer.parseInt(nodes.item(i + 3).getNodeValue()));
					wars.add(play);
				}
				
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/attacks/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				int numAttack = 1;
				for (int i = 1; i < nodes.getLength(); i+=15) {
					
					Attack currAtt = new Attack();
					currAtt.setPlayer(nodes.item(i).getNodeValue());
					currAtt.setThLevel(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					currAtt.setBaseRank(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
					currAtt.setStars(Integer.parseInt(nodes.item(i + 6).getNodeValue()));
					currAtt.setGhostStars(Integer.parseInt(nodes.item(i + 8).getNodeValue()));
					System.out.println(Integer.parseInt(nodes.item(i + 8).getNodeValue()));
					currAtt.setStar3(Boolean.parseBoolean(nodes.item(i + 10).getNodeValue()));
					currAtt.setWorth(Integer.parseInt(nodes.item(i + 12).getNodeValue()));
					currAtt.setAttNum(numAttack);
					currAtt.setWarNum(num);
					//System.out.println(currAtt.getGhostStars());
					wars.loadAttack(currAtt);
					numAttack++;
					
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/defenses/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				int defNumb = 1;
				for (int i = 1; i < nodes.getLength(); i+=7) {
					
					
					Defense currDef = new Defense();
					currDef.setPlayer(nodes.item(i).getNodeValue());
					currDef.setThLevel(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					currDef.setStars(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
					currDef.setDefNum(defNumb);
					currDef.setWarNum(num);
					wars.loadDefense(currDef);
					defNumb++;
					
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/EnemyTHs/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				if(nodes.getLength() > 0) {
					for (int i = 0; i < nodes.getLength(); i++) {
						wars.addEnTH(Integer.parseInt(nodes.item(i).getNodeValue()), i);
						
					}
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/EnemyClan//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				if(nodes.getLength() > 0) {
					for (int i = 0; i < nodes.getLength(); i++) {
						wars.setEnemyName(nodes.item(i).getNodeValue());
						
					}
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/EnemyStars//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				if(nodes.getLength() > 0) {
					for (int i = 0; i < nodes.getLength(); i++) {
						wars.setEnemyStars(Integer.parseInt(nodes.item(i).getNodeValue()));
						
					}
				}
				return wars;
		}
	}
	public war load14War(int num, String version) {
		if (!version.equals("1.4")) {
			return load13War(num);
		} else {
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				war wars = new war();
				Object result = null;
				int attackNum = 1;

				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/Participants/member/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}

				// Outputs the results of the query

				NodeList nodes = (NodeList) result;

				// Cycles through the results
				for (int i = 0; i < nodes.getLength(); i = i + 4) {
					Player play = new Player(nodes.item(i).getNodeValue());
					play.setAttackU(Integer.parseInt(nodes.item(i + 1).getNodeValue()));
					play.setAttackW(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					play.setStars(Integer.parseInt(nodes.item(i + 3).getNodeValue()));
					wars.add(play);
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/attacks/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				int numAttack = 1;
				for (int i = 1; i < nodes.getLength(); i+=13) {
					
					Attack currAtt = new Attack();
					currAtt.setPlayer(nodes.item(i).getNodeValue());
					currAtt.setThLevel(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					currAtt.setBaseRank(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
					currAtt.setStars(Integer.parseInt(nodes.item(i + 6).getNodeValue()));
					currAtt.setStar3(Boolean.parseBoolean(nodes.item(i + 8).getNodeValue()));
					currAtt.setWorth(Integer.parseInt(nodes.item(i + 10).getNodeValue()));
					currAtt.setAttNum(numAttack);
					currAtt.setWarNum(num);
					wars.loadAttack(currAtt);
					numAttack++;
					
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/defenses/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				int defNumb = 1;
				for (int i = 1; i < nodes.getLength(); i+=7) {
					
					
					Defense currDef = new Defense();
					currDef.setPlayer(nodes.item(i).getNodeValue());
					currDef.setThLevel(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					currDef.setStars(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
					currDef.setDefNum(defNumb);
					currDef.setWarNum(num);
					wars.loadDefense(currDef);
					defNumb++;
					
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/EnemyTHs/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				if(nodes.getLength() > 0) {
					for (int i = 0; i < nodes.getLength(); i++) {
						wars.addEnTH(Integer.parseInt(nodes.item(i).getNodeValue()), i);
						
					}
				}
				return wars;
		}
	}
	public war load13War(int num) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				war wars = new war();
				Object result = null;
				int attackNum = 1;

				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/Participants/member/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}

				// Outputs the results of the query

				NodeList nodes = (NodeList) result;

				// Cycles through the results
				for (int i = 0; i < nodes.getLength(); i = i + 4) {
					Player play = new Player(nodes.item(i).getNodeValue());
					play.setAttackU(Integer.parseInt(nodes.item(i + 1).getNodeValue()));
					play.setAttackW(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					play.setStars(Integer.parseInt(nodes.item(i + 3).getNodeValue()));
					wars.add(play);
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/attacks/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				int numAttack = 1;
				for (int i = 1; i < nodes.getLength(); i+=11) {
					
					Attack currAtt = new Attack();
					currAtt.setPlayer(nodes.item(i).getNodeValue());
					currAtt.setThLevel(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					currAtt.setBaseRank(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
					currAtt.setStars(Integer.parseInt(nodes.item(i + 6).getNodeValue()));
					currAtt.setStar3(Boolean.parseBoolean(nodes.item(i + 8).getNodeValue()));
					currAtt.setAttNum(numAttack);
					currAtt.setWarNum(num);
					wars.loadAttack(currAtt);
					numAttack++;
					
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/defenses/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				int defNumb = 1;
				for (int i = 1; i < nodes.getLength(); i+=7) {
					
					
					Defense currDef = new Defense();
					currDef.setPlayer(nodes.item(i).getNodeValue());
					currDef.setThLevel(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
					currDef.setStars(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
					currDef.setDefNum(defNumb);
					currDef.setWarNum(num);
					wars.loadDefense(currDef);
					defNumb++;
					
				}
				try {

					// Returns characters with the profession Student

					expr = xpath.compile("//wars/war" + num + "/EnemyTHs/*//text()");

					// Returns the result of the query

					result = expr.evaluate(doc, XPathConstants.NODESET);
					nodes = (NodeList) result;
					
				}

				catch (XPathExpressionException e) {

					e.printStackTrace();
				}
				if(nodes.getLength() > 0) {
					for (int i = 0; i < nodes.getLength(); i++) {
						wars.addEnTH(Integer.parseInt(nodes.item(i).getNodeValue()), i);
						
					}
				}
				return wars;
	}
	public Clan loadClan(String version) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		if(version.equals("1.1")) {
			//For saves without <removed> tags on members
			return get11Clan(doc, xpath);
		} else { 
			return getClan(doc, xpath);
		}
	}

	// Allows your app to get a parser that turns a xml doc into a DOM tree

	// Allows you to grab data from the document using the codes below

	private static Clan get11Clan(Document doc, XPath xpath) {

		// XPath Query
		XPathExpression expr;
		Clan clan = new Clan();
		Object result = null;

		try {

			// Returns characters with the profession Student

			expr = xpath.compile("//members/member/*//text()");

			// Returns the result of the query

			result = expr.evaluate(doc, XPathConstants.NODESET);
		}

		catch (XPathExpressionException e) {

			e.printStackTrace();
		}

		// Outputs the results of the query

		NodeList nodes = (NodeList) result;

		// Cycles through the results

		for (int i = 0; i < nodes.getLength(); i += 5) {
			Player play = new Player(nodes.item(i).getNodeValue());
			play.setThLevel(Integer.parseInt(nodes.item(i + 1).getNodeValue()));
			play.setRank(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
			play.setWorth(Double.parseDouble(nodes.item(i + 3).getNodeValue()));
			play.setNumWars(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
			clan.add(play);
		}
		try {

			// Returns characters with the profession Student

			expr = xpath.compile("//ClanInfo/*//text()");

			// Returns the result of the query

			result = expr.evaluate(doc, XPathConstants.NODESET);
		}

		catch (XPathExpressionException e) {

			e.printStackTrace();
		}
		nodes = (NodeList) result;

		// Cycles through the results
		for (int i = 0; i < nodes.getLength(); i+=4) {
			clan.setClanName(nodes.item(i).getNodeValue());
			clan.setUserName(nodes.item(i + 1).getNodeValue());
			clan.setPassword(nodes.item(i + 2).getNodeValue());
			clan.setSubreddit(nodes.item(i + 3).getNodeValue());
		}
		return clan;

		// Display every name
		// : //show/name//text()

		// Display everything
		// : //show/*//text()

		// Show names based on an attribute
		// : //show/name[@id_code='show_001']//text()

		// Show actors and character names
		// : //show/actors/actor/*//text()

		// Show character names if they are Students
		// : //show/actors/actor/character[@profession='Student']//text()

	}
	private static Clan getClan(Document doc, XPath xpath) {

		// XPath Query
		XPathExpression expr;
		Clan clan = new Clan();
		Object result = null;

		try {

			// Returns characters with the profession Student

			expr = xpath.compile("//members/member/*//text()");

			// Returns the result of the query

			result = expr.evaluate(doc, XPathConstants.NODESET);
		}

		catch (XPathExpressionException e) {

			e.printStackTrace();
		}

		// Outputs the results of the query

		NodeList nodes = (NodeList) result;

		// Cycles through the results

		for (int i = 0; i < nodes.getLength(); i += 6) {
			Player play = new Player(nodes.item(i).getNodeValue());
			play.setThLevel(Integer.parseInt(nodes.item(i + 1).getNodeValue()));
			play.setRank(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
			play.setWorth(Double.parseDouble(nodes.item(i + 3).getNodeValue()));
			play.setNumWars(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
			play.setInClan(Boolean.parseBoolean(nodes.item(i + 5).getNodeValue()));
			if(play.inClan()) {
				clan.add(play);
			} else {
				clan.addRemoved(play);
			}
		}
		try {

			// Returns characters with the profession Student

			expr = xpath.compile("//ClanInfo/*//text()");

			// Returns the result of the query

			result = expr.evaluate(doc, XPathConstants.NODESET);
		}

		catch (XPathExpressionException e) {

			e.printStackTrace();
		}
		nodes = (NodeList) result;

		// Cycles through the results
		for (int i = 0; i < nodes.getLength(); i+=4) {
			clan.setClanName(nodes.item(i).getNodeValue());
			clan.setUserName(nodes.item(i + 1).getNodeValue());
			clan.setPassword(nodes.item(i + 2).getNodeValue());
			clan.setSubreddit(nodes.item(i + 3).getNodeValue());
		}
		return clan;

		// Display every name
		// : //show/name//text()

		// Display everything
		// : //show/*//text()

		// Show names based on an attribute
		// : //show/name[@id_code='show_001']//text()

		// Show actors and character names
		// : //show/actors/actor/*//text()

		// Show character names if they are Students
		// : //show/actors/actor/character[@profession='Student']//text()

	}
	private static Clan getClanWithWars(Document doc, XPath xpath) {

		// XPath Query
		XPathExpression expr;
		Clan clan = new Clan();
		Object result = null;

		try {

			// Returns characters with the profession Student

			expr = xpath.compile("//members/member/*//text()");

			// Returns the result of the query

			result = expr.evaluate(doc, XPathConstants.NODESET);
		}

		catch (XPathExpressionException e) {

			e.printStackTrace();
		}

		// Outputs the results of the query

		NodeList nodes = (NodeList) result;

		// Cycles through the results

		for (int i = 0; i < nodes.getLength(); i += 5) {
			Player play = new Player(nodes.item(i).getNodeValue());
			play.setThLevel(Integer.parseInt(nodes.item(i + 1).getNodeValue()));
			play.setRank(Integer.parseInt(nodes.item(i + 2).getNodeValue()));
			play.setWorth(Double.parseDouble(nodes.item(i + 3).getNodeValue()));
			play.setNumWars(Integer.parseInt(nodes.item(i + 4).getNodeValue()));
			clan.add(play);
		}
		try {

			// Returns characters with the profession Student

			expr = xpath.compile("//ClanInfo/*//text()");

			// Returns the result of the query

			result = expr.evaluate(doc, XPathConstants.NODESET);
		}

		catch (XPathExpressionException e) {

			e.printStackTrace();
		}
		nodes = (NodeList) result;

		// Cycles through the results
		for (int i = 0; i < nodes.getLength(); i+=4) {
			clan.setClanName(nodes.item(i).getNodeValue());
			clan.setUserName(nodes.item(i + 1).getNodeValue());
			clan.setPassword(nodes.item(i + 2).getNodeValue());
			clan.setSubreddit(nodes.item(i + 3).getNodeValue());
		}
		return clan;

		// Display every name
		// : //show/name//text()

		// Display everything
		// : //show/*//text()

		// Show names based on an attribute
		// : //show/name[@id_code='show_001']//text()

		// Show actors and character names
		// : //show/actors/actor/*//text()

		// Show character names if they are Students
		// : //show/actors/actor/character[@profession='Student']//text()

	}
	public ArrayList<Integer>  getPlayerStars(String name) {
		ArrayList<Integer> stars = new ArrayList<>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				Object result = null;
				for (int i = 0; i <= numWars(); i++ ) {
					try {
	
						// Returns characters with the profession Student
	
						expr = xpath.compile("//wars/war" + i + "/Participants/member/*//text()");
	
						// Returns the result of the query
	
						result = expr.evaluate(doc, XPathConstants.NODESET);
					}
	
					catch (XPathExpressionException e) {
	
						e.printStackTrace();
					}
					NodeList nodes = (NodeList) result;
					//System.out.println(nodes.getLength());
					for (int num = 0; num < nodes.getLength(); num++) {
						if (nodes.item(num).getNodeValue().equals(name)) {
							stars.add(Integer.parseInt(nodes.item(num + 3).getNodeValue()));
							
						}
					}
				}
		
		return stars;
	}
	public ArrayList<Integer>  getPlayerPart(String name) {
		ArrayList<Integer> attacks = new ArrayList<>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				Object result = null;
				for (int i = 0; i <= numWars(); i++ ) {
					try {
	
						// Returns characters with the profession Student
	
						expr = xpath.compile("//wars/war" + i + "/Participants/member/*//text()");
	
						// Returns the result of the query
	
						result = expr.evaluate(doc, XPathConstants.NODESET);
					}
	
					catch (XPathExpressionException e) {
	
						e.printStackTrace();
					}
					NodeList nodes = (NodeList) result;
					//System.out.println(nodes.getLength());
					for (int num = 0; num < nodes.getLength(); num++) {
						if (nodes.item(num).getNodeValue().equals(name)) {
							attacks.add(Integer.parseInt(nodes.item(num + 1).getNodeValue()));
						}
					}
				}
		
		return attacks;
	}
	public ArrayList<Integer>  getPlayerWin(String name) {
		ArrayList<Integer> attacks = new ArrayList<>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				Object result = null;
				for (int i = 0; i <= numWars(); i++ ) {
					try {
	
						// Returns characters with the profession Student
	
						expr = xpath.compile("//wars/war" + i + "/Participants/member/*//text()");
	
						// Returns the result of the query
	
						result = expr.evaluate(doc, XPathConstants.NODESET);
					}
	
					catch (XPathExpressionException e) {
	
						e.printStackTrace();
					}
					NodeList nodes = (NodeList) result;
					//System.out.println(nodes.getLength());
					for (int num = 0; num < nodes.getLength(); num++) {
						if (nodes.item(num).getNodeValue().equals(name)) {
							attacks.add(Integer.parseInt(nodes.item(num + 2).getNodeValue()));
						}
					}
				}
		
		return attacks;
	}
	public ArrayList<ArrayList>  getPlayerRank(String name) {
		ArrayList<ArrayList> ret = new ArrayList<>();
		ArrayList<Attack> attacks = new ArrayList<>();
		ArrayList<Integer> warNum = new ArrayList<>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		// XPath Query
				XPathExpression expr;
				Object result = null;
				for (int i = 0; i <= numWars(); i++ ) {
					
					try {
	
						// Returns characters with the profession Student
	
						expr = xpath.compile("//wars/war" + i + "/attacks/*//text()");
	
						// Returns the result of the query
	
						result = expr.evaluate(doc, XPathConstants.NODESET);
					}
	
					catch (XPathExpressionException e) {
	
						e.printStackTrace();
					}
					NodeList nodes = (NodeList) result;
					//System.out.println(nodes.getLength());
					for (int num = 0; num < nodes.getLength(); num++) {
						
						if (nodes.item(num).getNodeValue().equals(name)) {
							Attack currAtt = new Attack();
							currAtt.setPlayer(nodes.item(num).getNodeValue());
							currAtt.setThLevel(Integer.parseInt(nodes.item(num + 2).getNodeValue()));
							currAtt.setBaseRank(Integer.parseInt(nodes.item(num + 4).getNodeValue()));
							currAtt.setStars(Integer.parseInt(nodes.item(num + 6).getNodeValue()));
							attacks.add(currAtt);
							warNum.add(i);
							
						}
					}
				}
				
		ret.add(attacks);
		ret.add(warNum);
		return ret;
	}

}