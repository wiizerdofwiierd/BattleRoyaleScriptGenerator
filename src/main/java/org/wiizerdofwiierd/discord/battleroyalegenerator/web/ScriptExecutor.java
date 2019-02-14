package org.wiizerdofwiierd.discord.battleroyalegenerator.web;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLInputElement;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.PanelGenerate;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class ScriptExecutor{
	
	private static final String URL_AGREE = "http://brantsteele.net/hungergames/agree.php";
	private static final String URL_SIZE = "http://brantsteele.net/hungergames/ChangeTributes-%d.php";
	private static final String URL_EDIT = "http://brantsteele.net/hungergames/edit.php";
	private static final String URL_VIEW = "http://brantsteele.net/hungergames/reaping.php";
	private static final String URL_SAVE = "http://brantsteele.net/hungergames/save.php";
	
	private static final String SAVE_PREFIX = "http://brantsteele.net/hungergames/r.php?c=";
	
	private PanelGenerate generatePanel;
	
	private boolean hasSetSize = false;
	private boolean hasExecuted = false;
	
	public ScriptExecutor(PanelGenerate generatePanel){
		this.generatePanel = generatePanel;
	}
	
	public void execute(String script){
		JFXPanel webPanel = new JFXPanel();

		Platform.runLater(() -> {
			WebView webView = new WebView();
			webPanel.setScene(new Scene(webView));

			WebEngine webEngine = webView.getEngine();
			webEngine.load(URL_AGREE);
			
			webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>(){
				@Override
				public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue){
					if(newValue == State.SUCCEEDED){
						
						String location = webEngine.getLocation();
						if(!ScriptExecutor.this.hasSetSize){
							System.out.println("Setting cast size...");

							int castSize = ScriptExecutor.this.generatePanel.getParentFrame().getSettings().getCastSize().getNumPlayers();
							webEngine.load(String.format(URL_SIZE, castSize));
							
							ScriptExecutor.this.hasSetSize = true;
							ScriptExecutor.this.generatePanel.setProgress(0.5F);
						}
						else if(!ScriptExecutor.this.hasExecuted){
							
							if(location.equals(URL_EDIT)){
								System.out.println("Executing script...");

								webEngine.executeScript(script);
								
								ScriptExecutor.this.hasExecuted = true;
								ScriptExecutor.this.generatePanel.setProgress(0.7F);

								NodeList nodes = webEngine.getDocument().getElementsByTagName("input");
								outer:
								for(int i = 0;i < nodes.getLength();i++){

									Node n = nodes.item(i);
									for(int j = 0;j < n.getAttributes().getLength();j++){

										String name = n.getAttributes().item(j).getNodeName();
										String value = n.getAttributes().item(j).getNodeValue();

										if(name.equals("value") && value.equals("Submit")){
											System.out.println("Submitting form...");
											HTMLInputElement submitButton = (HTMLInputElement) n;
											
											submitButton.click();
											ScriptExecutor.this.generatePanel.setProgress(0.8F);
											
											break outer;
										}
									}

								}
							}
							else{
								System.out.println("Loading edit page...");

								webEngine.load(URL_EDIT);
								ScriptExecutor.this.generatePanel.setProgress(0.6F);
							}
						}
						else if(location.equals(URL_VIEW)){
							System.out.println("Loading save page...");

							webEngine.load(URL_SAVE);
						}
						else if(location.equals(URL_SAVE)){
							NodeList nodes = webEngine.getDocument().getElementsByTagName("a");
							outer:
							for(int i = 0;i < nodes.getLength();i++){

								Node n = nodes.item(i);
								for(int j = 0;j < n.getAttributes().getLength();j++){

									String name = n.getAttributes().item(j).getNodeName();
									String value = n.getAttributes().item(j).getNodeValue();

									if(name.equals("href") && value.startsWith(SAVE_PREFIX)){
										System.out.println("Session link: " + value);
										presentURL(value);
										ScriptExecutor.this.generatePanel.setProgress(1.0F);
										webEngine.getLoadWorker().stateProperty().removeListener(this);

										return;
									}
								}

							}
						}
					}
				}
			});
		});
	}
	
	public void presentURL(String value){
		Util.openInBrowser(value);
		
		if(this.generatePanel.getParentFrame().getSettings().autoCopyLink()){
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
		}
	}
}
