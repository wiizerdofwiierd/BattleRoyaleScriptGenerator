package org.wiizerdofwiierd.discord.battleroyalegenerator.web;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLInputElement;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member.PanelGenerate;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public final class ScriptExecutor{
	
	private static final String URL_AGREE = "http://brantsteele.net/hungergames/agree.php";
	private static final String URL_SIZE = "http://brantsteele.net/hungergames/ChangeTributes-%d.php";
	private static final String URL_EDIT = "http://brantsteele.net/hungergames/edit.php";
	private static final String URL_VIEW = "http://brantsteele.net/hungergames/reaping.php";
	private static final String URL_SAVE = "http://brantsteele.net/hungergames/save.php";
	
	private static final String SAVE_REGEX = "^https?://brantsteele.net/hungergames/r.php\\?c=\\w+$";
	
	private PanelGenerate generatePanel;
	private Settings settings;
	
	private boolean hasSetSize = false;
	private boolean hasExecuted = false;
	
	public ScriptExecutor(PanelGenerate generatePanel, Settings settings){
		this.generatePanel = generatePanel;
		this.settings = settings;
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

							int castSize = ScriptExecutor.this.settings.castSize.getValue().getNumPlayers();
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

								submitForm(webEngine.getDocument());
								ScriptExecutor.this.generatePanel.setProgress(0.8F);
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
							
							for(int i = 0; i < nodes.getLength(); i++){

								System.out.println("Node " + i);
								
								Node n = nodes.item(i);
								for(int j = 0; j < n.getAttributes().getLength(); j++){

									String name = n.getAttributes().item(j).getNodeName();
									String value = n.getAttributes().item(j).getNodeValue();

									if(name.equals("href") && value.matches(SAVE_REGEX)){
										System.out.println("Session link: " + value);
										generatePanel.getTributesPanel().getMainWindow().setStatusBarText("Link generated: %s", value);
										
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
		
		if(this.settings.autoCopyLink.getValue()){
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
		}
	}
	
	private void submitForm(Document document){
		NodeList nodes = document.getElementsByTagName("input");
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

					break outer;
				}
			}

		}
	}
}
