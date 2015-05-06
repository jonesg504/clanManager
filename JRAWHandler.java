import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;






import javax.swing.JTextField;

import net.dean.jraw.ApiException;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.managers.AccountManager;
import net.dean.jraw.managers.AccountManager.SubmissionBuilder;
import net.dean.jraw.managers.InboxManager;
import net.dean.jraw.managers.ModerationManager;
import net.dean.jraw.models.Captcha;
import net.dean.jraw.models.CommentNode;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.SubredditPaginator;

public class JRAWHandler {
	protected static Submission claimSubmission;
	private static String claimPost = 
			"**Please comment: \"Name : BaseClaim\"**  \n";
			
	private static RedditClient redditClient;
	private static Credentials credentials;
	private static OAuthData authData;
	private static AccountManager manage;
	private static String capAt;
	private static Captcha cap;
	private static boolean needsCap = false;
	private static boolean needsNewCap = false;
	public static ModerationManager mod;
	private static UserAgent myUserAgent;
	private static String subreddit;
	public JRAWHandler(String username, String password, String subreddit) {
		this.subreddit = subreddit;
		password = mainWindow.decrypt(password);
		myUserAgent = UserAgent.of("desktop", "warTracking",
				"0.1", "ClanManagerApp");
		redditClient = new RedditClient(myUserAgent);
		credentials = Credentials.script(username,
				password, "t-X5oYlOfO5D7w",
				"gaaZ4NO0sQ3c6IO78fyhWJ6NpL0");
		
		try {
			authData = redditClient.getOAuthHelper().easyAuth(credentials);
		} catch (NetworkException | OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		redditClient.authenticate(authData);
		manage = new AccountManager(redditClient);
		mod = new ModerationManager(redditClient);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Looks like your username or password are incorrect");
		}
		
		if(redditClient.needsCaptcha()) {
			getNewCaptcha();
		}
	}
	public static void MakeClaimPost() {
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SubmissionBuilder builder = new SubmissionBuilder(claimPost,
					subreddit, "War Claim for " + dateFormat.format(date));
			try {
				claimSubmission = manage.submit(builder);
				mod.setSticky(claimSubmission, true);
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(redditClient.me());
		} catch (NetworkException e) {
			e.printStackTrace();
		}

	}
	public static void getNewCaptcha() {
		needsCap = true;
		java.awt.Image image = null;
		try {
			cap = redditClient.getNewCaptcha();
			URL url = cap.getImageUrl();
			
			image = java.awt.Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(url);
		} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		JPanel pane = new JPanel();
		//pane.setLayout(new GridLayout(2,1));
		JPanel holder = new JPanel();
        JLabel label = new JLabel(new ImageIcon(image));
        JTextField input = new JTextField("Enter Captcha");
       // holder.add(input);
        pane.add(label);
        pane.add(input);
        Object[] options1 = { "Authorize", "New Captcha" };
		int result = JOptionPane.showOptionDialog(null, pane,
				"Captcha", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options1, null);
		if (result == 0) {
			capAt = input.getText();
			System.out.println(capAt);
		} else if( result == 1) {
			getNewCaptcha();
		}
	}
	public static void MakeWarStatPost(war war, String topAttacks, int number) {
		String warPost = "";
		double starDat = war.getStars();
		double perf = (starDat / (double)(war.getNumPlayers() * 3)) * 100;
		perf = Math.round(perf * 10) / 10;
		double part = war.parting();
		part = Math.round(part * 10) / 10;
		int losses = (int)war.losing();
		losses = Math.round(losses * 10) / 10;
		warPost = "Performance: " + perf + "% of stars  \n" +
				"Participation: " + part + "% of attacks used  \n" +
				"Wasted Attacks: " + losses + " attacks wasted  \n";
		warPost += topAttacks;
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String input = JOptionPane.showInputDialog("Add Tag to Post (e.g [OFFICIAL])");
			SubmissionBuilder builder = new SubmissionBuilder(warPost,
					subreddit, input + "War Stats for War vs. " + war.getEnemyName());
			try {
				if(needsCap) {
					if(needsNewCap) {
						getNewCaptcha();
					}
					claimSubmission = manage.submit(builder, cap, capAt);
					needsNewCap = true;
				} else {
					claimSubmission = manage.submit(builder);
				}
			} catch (ApiException e) {
				needsNewCap = true;
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(errors.toString());
//				clipboard.setContents(strSel, null);
				JPanel pane = new JPanel();
				pane.setLayout(new GridLayout(2,1));
				JLabel label = new JLabel("Error: " + e.getReason() + ": "+ e.getExplanation());
				JLabel label2 = new JLabel("If it isnt BAD captcha or Rate limit Error: Click below to copy to clipboard and email me with it");
		       // holder.add(input);
		        pane.add(label);
		        pane.add(label2);
				Object[] options1 = { "Copy To ClipBoard", "Cancel" };
				int result = JOptionPane.showOptionDialog(null, pane,
						"Captcha", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options1, null);
				if(result == 0) {
					clipboard.setContents(strSel, null);
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(redditClient.me());
		} catch (NetworkException e) {
			e.printStackTrace();
		}

	}
	public static void MakeLeaderBoardsPost(war war, String top5FromTH, String board, boolean wholeBoard) throws Exception {
		String warPost = "";
		warPost = top5FromTH;
		if(wholeBoard) {
			warPost += board;
		}
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String input = JOptionPane.showInputDialog("Add Tag to Post (e.g [OFFICIAL])");
			SubmissionBuilder builder = new SubmissionBuilder(warPost,
					subreddit, input + "Leaderboard as of " + dateFormat.format(date));
			try {
				if(needsCap) {
					if(needsNewCap) {
						getNewCaptcha();
					}
					claimSubmission = manage.submit(builder, cap, capAt);
					needsNewCap = true;
				} else {
					claimSubmission = manage.submit(builder);
				}
			} catch (ApiException e) {
				needsNewCap = true;
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(errors.toString());
//				clipboard.setContents(strSel, null);
				JPanel pane = new JPanel();
				pane.setLayout(new GridLayout(2,1));
				JLabel label = new JLabel("Error: " + e.getReason() + ": "+ e.getExplanation());
				JLabel label2 = new JLabel("If it isnt BAD captcha or Rate limit Error: Click below to copy to clipboard and email me with it");
		       // holder.add(input);
		        pane.add(label);
		        pane.add(label2);
				Object[] options1 = { "Copy To ClipBoard", "Cancel" };
				int result = JOptionPane.showOptionDialog(null, pane,
						"Captcha", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options1, null);
				if(result == 0) {
					clipboard.setContents(strSel, null);
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(redditClient.me());
		} catch (NetworkException e) {
			e.printStackTrace();
		}

	}
	public static HashMap<Integer, String> updateClaimsPost(int warSize) {
		CommentNode node  = getCommentNodeSticky();
		HashMap<Integer, String> selfPost = new HashMap<Integer, String>();
		for(int i = 1; i <= warSize - 5; i++) {
			selfPost.put(i, "  \n");
		}
		//selfPost.put(warSize - 4, "**" + (warSize - 4) + "-" + warSize + " must attack straight across for their first attack!**");
		HashMap<String, String> commentMap = new HashMap<String, String>();
		ArrayList<String> usernames = new ArrayList<>();
		for(CommentNode n : node.walkTree()) {
        	commentMap.put(n.getComment().getAuthor(), n.getComment().getBody());
        	usernames.add(n.getComment().getAuthor());
        	//String intValue = n.getComment().getBody().replaceAll("[.,A-Za-z:-]+", "");
        	//String[] values = intValue.split("[ ]");
        	//System.out.println(values.length);
        	//System.out.println(intValue);
        }
		Iterator it = commentMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        if(pair.getKey().equals("razzark666")) {
	        	//System.out.println("I FOUND NIKKO");
	        	String[] values = ((String) pair.getValue()).split("[ \\n]");
	        	int value = 0;
	        	int claim;
	        	for(int i = 0; i < values.length; i++ ) {
	        		if(values[i].contains("ikko")) {
	        			if (values[i+1].equals(":")){
	        				claim = Integer.parseInt(values[i+2]);
	        			} else {
	        				claim = Integer.parseInt(values[i+1]);
	        			}
	        			selfPost.put(claim, " Nikko  ");
	        		}
	        		if(values[i].contains("ax")) {
	        			if (values[i+1].equals(":")){
	        				claim = Integer.parseInt(values[i+2]);
	        			} else {
	        				claim = Integer.parseInt(values[i+1]);
	        			}
	        			selfPost.put(claim, " Max  ");
	        		}
	        	}
	        	it.remove();
	        } else {
	        	String claim = (String) pair.getValue();
	        	claim = claim.replaceAll("[ .,A-Za-z:-]+", "");
	        	claim = claim.replaceAll("[ &;'#?]+", "");
	        	
	        	//System.out.println(claim);
	        	int intClaim = Integer.parseInt(claim);
	        	//System.out.println(intClaim);
	        	if(intClaim > 50) {
	        		InboxManager sender = new InboxManager(redditClient);
	        		try {
	        			JOptionPane.showMessageDialog(null, (String)pair.getKey() + " did not format reply correctly");
						//sender.compose((String)pair.getKey(), "War Claims Post", "Please format your claims: \"<Name> : <BaseClaim>\"");
					} catch (NetworkException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	} else if (intClaim > (warSize - 5)){
	        		System.out.println();
	        	} else {
		        	selfPost.put(intClaim, (String) pair.getKey());
	        	}
		        //System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
	        }
	    }
	    //it = selfPost.entrySet().iterator();
//	    while (it.hasNext()) {
//	    	Map.Entry pair = (Map.Entry)it.next();
//	    	System.out.println(pair.getKey() + " = " + pair.getValue());
//	    	it.remove();
//	    }
	    return selfPost;
	}
	public static String editPost(int warSize) {
		HashMap<Integer, String> map = updateClaimsPost(warSize); 
		SubredditPaginator sub = new SubredditPaginator(redditClient, subreddit);
		sub.setSubreddit(subreddit);
		sub.next();
		String returned = "Claims:  \n";
		String post = "**Please comment: \"<Name : BaseClaim\"**  \n";
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			post += "" + pair.getKey() + ": " + pair.getValue() + "   \n";
			if(!pair.getValue().equals("  \n")) {
				returned += pair.getValue() + ": " + pair.getKey() + "  \n";
			}
		}
		post += "**" + (warSize - 4) + "-" + warSize + " must attack straight across for their first attack!**";
		for (Submission s : sub.getCurrentListing()) {
			if(s.isStickied()) {
			    Submission fullSubmissionData = redditClient.getSubmission(s.getId());
		        try {
					manage.updateSelfpost(fullSubmissionData, post);
				} catch (NetworkException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
			}
		}
		return returned;
	}
	public static CommentNode getCommentNodeSticky() {
		SubredditPaginator sub = new SubredditPaginator(redditClient, subreddit);
		sub.setSubreddit(subreddit);
		sub.next();
		for (Submission s : sub.getCurrentListing()) {
			if(s.isStickied()) {
			    Submission fullSubmissionData = redditClient.getSubmission(s.getId());
		        //System.out.println(fullSubmissionData.getTitle());
		        return fullSubmissionData.getComments();
			}
		}
		
		return null;
	}
	public static void editClaim() {
		try {
			manage.updateSelfpost(claimSubmission, "EDITED");
		} catch (NetworkException | ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
