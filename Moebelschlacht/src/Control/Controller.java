package Control;


import java.util.ArrayList;
import java.util.Optional;

import Control.ClientServer.Client;
import Model.Model;
import View.View;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Controller extends Client implements EventHandler<ActionEvent> {

	private Model mod;
	private View vi;
	Boolean[][] yx = new Boolean[11][11];
	int a = 0;
	int b = 0;
	int c = 0;
	int d = 0;
	int tf = 2;
	int anzahlfelder = 0;
	int zsp = 0;
	Pane p = new Pane();
	ArrayList<Pane>arli = new ArrayList<>();
	int yourships = 0;
	int friendsships = 0;
	boolean treffer = false;
	int anzahlgetroffenyou = 0;
	int anzahlgetroffenfriend = 0;
	boolean ready = false;

	public Controller(String ip_address, int serverPort, Model mod, View vi) {
		super(ip_address, serverPort);
		this.mod = mod;
		this.vi = vi;
		setzenfalse();
	}

	public Controller(String ip_address, int localPort, int serverPort, Model mod, View vi) {
		super(ip_address, localPort, serverPort);
		this.mod = mod;
		this.vi = vi;
		setzenfalse();
	}


	public void setzenfalse() {
		for (int r = 0; r < yx.length; r++) {
			for (int i = 0; i < yx.length; i++) {
				yx[i][r] = false;
				Pane l = new Pane();
				vi.getGrid().add(l, i + 1, r + 1);
			}
		}
	}

	@Override
	public void handle(ActionEvent event) {

	}

	public void schiff1() {

		if (tf % 2 == 0) {
			vi.getB().setDisable(true);
			vi.getC().setDisable(true);
			vi.getD().setDisable(true);
			visibleKordinatenAngabe();
			vi.getAchtung().setText("Attention: 2 fields");
			a++;
			anzahlfelder = 2;
			tf++;
			zsp = 2;
		} else {
			vi.visible();
			vi.getB().setDisable(false);
			vi.getC().setDisable(false);
			vi.getD().setDisable(false);
			tf = 0;
			a--;
		}
	}

	public void schiff2() {
		if (tf % 2 == 0) {
			vi.getA().setDisable(true);
			vi.getC().setDisable(true);
			vi.getD().setDisable(true);
			visibleKordinatenAngabe();
			vi.getAchtung().setText("Attention: 3 fields");
			b++;
			anzahlfelder = 3;
			tf++;
			zsp = 3;
		} else {
			vi.getA().setDisable(false);
			vi.getC().setDisable(false);
			vi.getD().setDisable(false);
			vi.visible();
			tf = 0;
			b--;
		}
	}

	public void schiff3() {
		if (tf % 2 == 0) {
			vi.getA().setDisable(true);
			vi.getB().setDisable(true);
			vi.getD().setDisable(true);
			visibleKordinatenAngabe();
			vi.getAchtung().setText("Attention: 4 fields");
			c++;
			anzahlfelder = 4;
			tf++;
			zsp = 4;
		} else {
			vi.getA().setDisable(false);
			vi.getB().setDisable(false);
			vi.getD().setDisable(false);
			vi.visible();
			tf = 0;
			c--;
		}

	}

	public void schiff4() {

		if (tf % 2 == 0) {
			vi.getA().setDisable(true);
			vi.getB().setDisable(true);
			vi.getC().setDisable(true);
			visibleKordinatenAngabe();
			vi.getAchtung().setText("Attention: 5 fields");
			d++;
			anzahlfelder = 5;
			tf++;
			zsp = 5;
		} else {
			vi.getA().setDisable(false);
			vi.getB().setDisable(false);
			vi.getC().setDisable(false);
			vi.visible();
			tf = 0;
			d--;
		}
	}

	public void visibleKordinatenAngabe() {
		vi.getLposition().setVisible(true);
		vi.getxKoordinateAnfang().setVisible(true);
		vi.getyKoordinateAnfang().setVisible(true);
		vi.getRb1().setVisible(true);
		vi.getRb2().setVisible(true);
		vi.getxKanfang().setVisible(true);
		vi.getyKanfang().setVisible(true);
		vi.getAnfang().setVisible(true);
		vi.getEnde().setVisible(true);
		vi.getOk().setVisible(true);
		vi.getAchtung().setVisible(true);
		vi.getSubmit().setVisible(false);
		vi.getClear().setVisible(false);

	}

	public void okay() {

		fertig();
		vi.getA().setDisable(false);
		vi.getB().setDisable(false);
		vi.getC().setDisable(false);
		vi.getD().setDisable(false);
		// pr�fen wie oft genutzt
		if (a == 4) {
			vi.getA().setDisable(true);
		}
		if (b == 3) {
			vi.getB().setDisable(true);
		}
		if (c == 2) {
			vi.getC().setDisable(true);
		}
		if (d == 1) {
			vi.getD().setDisable(true);
		}
		// Schiff einf�gen
		vi.visible();
		if ((a + b + c + d) ==10) {
			auswahlfertig();
		}
		tf = 0;

	}

	public void fertig() {
		p = new Pane();

		if (vi.getyKoordinateAnfang().getSelectionModel().getSelectedItem() == null
				|| vi.getxKoordinateAnfang().getSelectionModel().getSelectedItem() == null
				|| vi.getGroup().getSelectedToggle().getUserData() == null) {

		} else {
			String sp = vi.getGroup().getSelectedToggle().getUserData().toString(); // Radiobutton
			String k1 = vi.getyKoordinateAnfang().getSelectionModel().getSelectedItem().toString(); // Koordintate zahl
			String k2 = vi.getxKoordinateAnfang().getSelectionModel().getSelectedItem(); // Koordinate buchstabe
			
		
			 p.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
						BorderWidths.DEFAULT, Insets.EMPTY)));
				p.setBackground(new Background(new BackgroundFill(Color.MEDIUMSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				arli.add(p);
				if (sp == "ver") {
					
					if (zsp<=yx.length-Integer.parseInt(k1)&&yx[abc(k2)][Integer.parseInt(k1)] == false) { // pr�fen ob koordinate frei ist

						vi.getGrid().add(p, abc(k2), Integer.parseInt(k1), 1, zsp);
						for (int i = 0; i < zsp; i++) {
							yx[abc(k2)][Integer.parseInt(k1)+i] = true;
						}
					} else {
						welchesschiff();
					}
				} else {

					if (zsp<=yx.length-abc(k2) && yx[abc(k2)][Integer.parseInt(k1)] == false) {
						//pr�fen od eh alle frei sind
						
						vi.getGrid().add(p, abc(k2), Integer.parseInt(k1), zsp, 1); 
						for (int i = 0; i < zsp; i++) {
							yx[abc(k2)+i][Integer.parseInt(k1)] = true;
						}
						
						
					} else {
						
						welchesschiff();
					}
				}
				
			}

		 
						
	}

	public void welchesschiff() // anzahl verwendet verringern
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Attention");
		alert.setHeaderText("");
		String s ="Please choose other coordinates!";
		alert.setContentText(s);
		alert.show();
		if (vi.getA().isDisabled() == false) {
			a--;
		}
		if (vi.getB().isDisabled() == false) {
			b--;
		}
		if (vi.getC().isDisabled() == false) {
			c--;
		}
		if (vi.getD().isDisabled() == false) {
			d--;
		}

	}

	public void auswahlfertig() {
		vi.getSubmit().setDisable(false);
		
	}

	public int abc(String s) {
		int w = 0;
		switch (s) {
		case "A":
			w = 1;
			break;
		case "B":
			w = 2;
			break;
		case "C":
			w = 3;
			break;
		case "D":
			w = 4;
			break;
		case "E":
			w = 5;
			break;
		case "F":
			w = 6;
			break;
		case "G":
			w = 7;
			break;
		case "H":
			w = 8;
			break;
		case "I":
			w = 9;
			break;
		case "J":
			w = 10;
			break;
		}
		return w;
	}

	public void clear() {

		for (int i = 0; i < arli.size(); i++) {
			vi.getGrid().getChildren().remove(arli.get(i));
		}
		for (int r = 1; r < yx.length; r++) {
			for (int i = 1; i < yx.length; i++) {
				if(yx[r][i] == true)
				{
					vi.getGrid().add(new Pane(), r, i);
					yx[r][i] = false;
				}
			}
		}
		vi.getA().setDisable(false);
		vi.getB().setDisable(false);
		vi.getC().setDisable(false);
		vi.getD().setDisable(false);
		a = 0;
		b = 0;
		c = 0;
		d = 0;
	}
	
	public void submit()
	{
		GridPane p =(GridPane) vi.getCenter();
		p.setMinSize(200,600);
		vi.setCenter(vi.getPane1());
		vi.setFriend(vi.feld(new GridPane()));
		vi.setLeft(vi.getFriend());
		vi.setRight(p);
		vi.right();
		sendMessage("\\ready");
		if(!ready) {
			ready = true;
		}
	}

	public void send()
	{
		sendMessage(vi.getFeld().getText());
		vi.getInput().appendText("You: " + vi.getFeld().getText() + "\n");
	}
	
	@Override
	public void receiveMessage(String s) {
		//TODO Das ist für den Chat; Die Nachricht ist der Parameter s
		vi.getInput().appendText("Friend: "+ s + "\n");
		vi.getFeld().setText("");
	}

	

	/**
	 * Hier kommen nur Nachrichten an die mit einem Backslash anfagen an. so kommunizieren die Server verdeckt ohne das es im Chat ist.
	 *
	 * @param message - The command that is received
	 */
	@Override
	public void receiveCommand(String message) {
		message = message.replace("\\","");
		String[] str = message.split(" ");
		switch(str[0]){
			case "ready":
				if(!ready) {
					vi.getxGegner().setDisable(true);
					vi.getyGegner().setDisable(true);
					vi.getSubmit2().setDisable(true);
					Platform.runLater(() -> vi.setTurnText("Friends Turn"));
					ready = !ready;
				}else{
					vi.getxGegner().setDisable(false);
					vi.getyGegner().setDisable(false);
					vi.getSubmit2().setDisable(false);
					Platform.runLater(() -> vi.setTurnText("Your Turn"));
					ready = !ready;
				}
				break;

			case "miss":
				//TODO ein Feld Gelb setzen wenn man daneben schießt (du schießt)
				Pane p = new Pane();
				p.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
				Platform.runLater(() -> vi.getFriend().add(p, abc(str[1]), Integer.parseInt(str[2])));
				break;

			case "hit":
				//TODO ein Feld rot setzten wenn man trifft (du schießt)
				Pane p2 = new Pane();
				p2.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
				Platform.runLater(() -> {
					GridPane pane = vi.getFriend();
					pane.add(p2,abc(str[1]), Integer.parseInt(str[2]));
					vi.setFriend(pane);
				});
				anzahlgetroffenyou++;
				break;

			case "shoot":
				//TODO im nächsten index stehen koordinaten auf denen geschossen wurde und du sagst treffer oder nicht (gegner schießt)
				if(yx[abc(str[1])][Integer.parseInt(str[2])])
				{
					sendMessage("\\hit " + str[1] + " " + str[2]);
					Pane pr = new Pane();
					pr.setBackground(new Background(new BackgroundFill(Color.DARKRED,CornerRadii.EMPTY,Insets.EMPTY)));
					Platform.runLater(() -> vi.getGrid().add(pr, abc(str[1]),Integer.parseInt(str[2])));
					anzahlgetroffenfriend++;
				}
				else
				{
					sendMessage("\\miss " + str[1] + " " + str[2]);
					Pane pr = new Pane();
					pr.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY,Insets.EMPTY)));
					Platform.runLater(() -> vi.getGrid().add(pr, abc(str[1]),Integer.parseInt(str[2])));
				}
				break;
				
		}
		
		if(anzahlgetroffenyou==30)
		{
			Platform.runLater(() ->{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Winner");
				alert.setHeaderText(null);
				alert.setContentText("You Won! :)");

				alert.showAndWait();
				System.exit(0);
			});

		}
		if(anzahlgetroffenfriend==30)
		{
			Platform.runLater(() -> {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Loser");
				alert.setHeaderText(null);
				alert.setContentText("You Lost! :(");

				alert.showAndWait();
				System.exit(0);
			});

		}
	}

	
	public void submit2()
	{
		sendMessage("\\shoot "+vi.getxGegner().getSelectionModel().getSelectedItem() + " " + vi.getyGegner().getSelectionModel().getSelectedItem());
		sendMessage("\\ready");
		vi.getxGegner().setDisable(true);
		vi.getyGegner().setDisable(true);
		vi.getSubmit2().setDisable(true);
		Platform.runLater(() -> vi.setTurnText("Friends Turn"));
		ready = !ready;
	}
}
