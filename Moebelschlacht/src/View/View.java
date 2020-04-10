package View;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Model.*;

import Control.*;

import java.util.Optional;


public class View extends BorderPane{

	Controller con;
	Model mod;
	Stage primaryStage;
	Button a = new Button();
	Button b = new Button();
	Button c = new Button();
	Button d = new Button();
	Button ok = new Button();
	Pane pane = new Pane();
	Label l = new Label();
	Label lposition = new Label();
	GridPane grid = new GridPane();
	ChoiceBox<String> xKoordinateAnfang = new ChoiceBox<String>();
	ChoiceBox<Integer> yKoordinateAnfang = new ChoiceBox<Integer>();
	Label xKanfang = new Label("x-coordinate");
	Label yKanfang = new Label("y-coordinate");
	Label anfang = new Label("Start");
	Label orientierung = new Label("Orientation");
	Label achtung = new Label();
	Button clear = new Button("Clear");
	Button submit = new Button("Submit");
	ToggleGroup group = new ToggleGroup();
	RadioButton rb1 = new RadioButton();
	RadioButton rb2 = new RadioButton();
	
	Pane pane1 = new Pane();
	TextArea input = new TextArea();
	TextField feld = new TextField();
	Button send = new Button("Send");
	Label an = new Label("Attack:");
	Label x = new Label("x-coordinate");
	Label y = new Label("y-coordinate");	
	ChoiceBox<String> xGegner = new ChoiceBox<String>();
	ChoiceBox<Integer> yGegner = new ChoiceBox<Integer>();
	Button submit2 = new Button("Submit");
	Label turn = new Label("Wait for Friend");

	public GridPane getFriend() {
		return friend;
	}

	public void setFriend(GridPane friend) {
		this.friend = friend;
	}

	GridPane friend;

	public Label getTurn() {
		return turn;
	}


	public void setTurn(Label turn) {
		this.turn = turn;
	}


	public View(Stage primaryStage) {
		this.primaryStage = primaryStage;
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter your Friends IP address:");
		Optional<String> result = dialog.showAndWait();
		mod = new Model();
		result.ifPresent(s -> con = new Controller(s,65535,mod, this));
		start();
	}
	
	
	public void start()
	{
		anweisungstexte();
		schiffeErzeugen();
		koordinaten();
		grid = feld(new GridPane());
		
		pane.setPrefSize(200, 600);
		pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY,Insets.EMPTY)));
		pane.getChildren().addAll(l,a,b,c,d, lposition);
		setLeft(pane);
		setCenter(grid);
		primaryStage.setOnCloseRequest(e -> {
			System.exit(0);
		});
	}
	
	public void anweisungstexte()
	{
		l.setText("Choose a ship:");
		l.setPadding(new Insets(20, 10, 10, 40));
		l.setFont(new Font("Arial", 16));
		l.setStyle("-fx-font-weight: bold");
		
		lposition.setText("       Specify the \n position of the ship:");
		lposition.setPadding(new Insets(10, 10, 10, 20));
		lposition.setFont(new Font("Arial", 16));
		lposition.setLayoutY(330);
		lposition.setStyle("-fx-font-weight: bold");
	}
	
	public GridPane feld(GridPane grid)
	{
		
		grid.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
		grid.setGridLinesVisible(true);
        final int numCols = 11 ;
        final int numRows = 11 ;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            grid.getColumnConstraints().add(colConst);
            //Buchstaben - xKoordinaten
            Label t = new Label();
            t.setPadding(new Insets(0, 0, 0, 27));
            t.setText(""+(char) (i+64));
            t.setFont(new Font("Arial", 16));
            if(i>0)
            {
            	grid.add(t, i, 0);          	
            }   
            if(i==0)
            {
            	Label x = new Label();
            	x.setPrefSize(65, 65);
            	x.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
    	        BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
    	        CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
            	x.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY,Insets.EMPTY)));
            	grid.add(x, i, 0);
            }
            t.setPrefSize(65, 65);
        	t.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	        BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
	        CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        	t.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY,Insets.EMPTY)));
        }
        
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            grid.getRowConstraints().add(rowConst);  
            // Zahlen - yKoordinaten
            Label t = new Label();
            t.setPadding(new Insets(0, 0, 0, 25));
            t.setText(""+i);
            t.setFont(new Font("Arial", 16));
            if(i>0)
            {
            	grid.add(t, 0, i);
            }  
            t.setPrefSize(65, 65);
        	t.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
	        BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
	        CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        	t.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY,Insets.EMPTY)));
        }
        return grid;
	}
	
	public void schiffeErzeugen()
	{
		ImageView va = new ImageView(new Image("img/Schiff4.PNG"));
		va.setFitHeight(50);
		va.setFitWidth(140);
		ImageView vb = new ImageView(new Image("img/Schiff1.PNG"));
		vb.setFitHeight(50);
		vb.setFitWidth(140);
		ImageView vc = new ImageView(new Image("img/Schiff2.PNG"));
		vc.setFitHeight(50);
		vc.setFitWidth(140);
		ImageView vd = new ImageView(new Image("img/Schiff3.PNG"));		
		vd.setFitHeight(50);
		vd.setFitWidth(140);
		
		
		a.setGraphic(va);	
		a.setUserData("a");
		b.setGraphic(vb);	
		b.setUserData("b");
		c.setGraphic(vc);	
		c.setUserData("c");
		d.setGraphic(vd);
		d.setUserData("d");
		
		
		// 1 - 5 felder
		// 2 - 4
		// 3 - 3
		// 4 - 2 
		
		a.setLayoutY(60);
		a.setLayoutX(20);
		a.setPrefSize(160, 50);
		b.setLayoutY(130);
		b.setLayoutX(20);
		b.setPrefSize(160, 50);
		c.setLayoutY(200);
		c.setLayoutX(20);
		c.setPrefSize(160, 50);
		d.setLayoutY(270);
		d.setLayoutX(20);
		d.setPrefSize(160, 50);
		
		a.setOnAction(event-> con.schiff1());
		b.setOnAction(event-> con.schiff2());
		c.setOnAction(event-> con.schiff3());
		d.setOnAction(event-> con.schiff4());
	}
	
	public void koordinaten()
	{		
		//Anfang
		anfang.setFont(new Font("Arial", 16));
		anfang.setLayoutX(10);
		anfang.setLayoutY(395);
		anfang.setPrefSize(180, 20);
		anfang.setBorder(new Border(new BorderStroke(Color.RED, Color.RED, Color.BLACK, Color.RED,
		        BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
		        CornerRadii.EMPTY, new BorderWidths(3), Insets.EMPTY)));
		
		xKanfang.setFont(new Font("Arial", 16));
		xKanfang.setLayoutX(20);
		xKanfang.setLayoutY(440);
			
		yKanfang.setFont(new Font("Arial", 16));
		yKanfang.setLayoutX(20);
		yKanfang.setLayoutY(480);
		
		//Choicebox
		xKoordinateAnfang.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
		xKoordinateAnfang.setLayoutX(125);
		xKoordinateAnfang.setLayoutY(435);
		
		yKoordinateAnfang.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
		yKoordinateAnfang.setLayoutX(120);
		yKoordinateAnfang.setLayoutY(475);
		
		
		
		// --------------------------------------------------------------------------------------------------------
		
		//Orientierung
		orientierung.setFont(new Font("Arial", 16));
		orientierung.setLayoutX(10);
		orientierung.setLayoutY(520);
		orientierung.setPrefSize(180, 20);
		orientierung.setBorder(new Border(new BorderStroke(Color.RED, Color.RED, Color.BLACK, Color.RED,
		        BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
		        CornerRadii.EMPTY, new BorderWidths(3), Insets.EMPTY)));
		
		
		
		Label v = new Label("vertical");
		v.setFont(new Font("Arial", 16));	
		rb1.setToggleGroup(group);
		rb1.setText(v.getText());
		rb1.setLayoutX(20);
		rb1.setLayoutY(570);
		rb1.setUserData("ver");
		
		Label h = new Label("horizontal");
		h.setFont(new Font("Arial", 16));
		rb2.setToggleGroup(group);
		rb2.setText(h.getText());
		rb2.setLayoutX(20);
		rb2.setLayoutY(600);
		rb2.setUserData("hor");
		
		//OK 
		ok.setText("Submit");
		ok.setLayoutX(20);
		ok.setLayoutY(670);
		ok.setPrefSize(160, 20);
		ok.setOnAction(event->con.okay());

		achtung.setPadding(new Insets(10, 10, 10, 35));
		achtung.setFont(new Font("Arial", 14));
		achtung.setStyle("-fx-font-weight: bold");
		achtung.setLayoutY(638);
		
		clear.setPrefSize(160, 25);
		clear.setLayoutX(20);
		clear.setLayoutY(620);
		clear.setOnAction(event->con.clear());
		
		submit.setPrefSize(160, 40);
		submit.setLayoutX(20);
		submit.setLayoutY(660);
		submit.setOnAction(event->con.submit());
		
		visible();
		
		pane.getChildren().addAll(rb1, rb2,submit, clear, xKanfang,yKanfang, xKoordinateAnfang, yKoordinateAnfang, anfang, orientierung, ok, achtung);
	}

	public void visible()
	{
		rb1.setVisible(false);
		rb2.setVisible(false);
		clear.setVisible(true);
		submit.setVisible(true);
		submit.setDisable(true);
		lposition.setVisible(false);
		xKoordinateAnfang.setVisible(false);
		yKoordinateAnfang.setVisible(false);
		xKanfang.setVisible(false);
		yKanfang.setVisible(false);
		ok.setVisible(false);
		anfang.setVisible(false);
		orientierung.setVisible(false);
		achtung.setVisible(false);
	}

	public void right()
	{
		pane1.setPrefSize(200, 600);
		pane1.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY,Insets.EMPTY)));		
		
		input.setPrefSize(180, 400);
		input.setEditable(false);
		input.setLayoutX(10);
		input.setLayoutY(10);		
		
		feld.setPrefSize(180, 20);
		feld.setLayoutX(10);
		feld.setLayoutY(425);

		send.setPrefSize(180, 30);
		send.setLayoutX(10);
		send.setLayoutY(460);
		send.setOnAction(event->con.send());
			
		an.setFont(new Font("Arial", 16));
		an.setLayoutX(10);
		an.setLayoutY(510);
		an.setPrefSize(180, 20);
		an.setBorder(new Border(new BorderStroke(Color.RED, Color.RED, Color.BLACK, Color.RED,
		        BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
		        CornerRadii.EMPTY, new BorderWidths(3), Insets.EMPTY)));
				
		x.setFont(new Font("Arial", 16));
		x.setLayoutX(20);
		x.setLayoutY(555);
			
			
		y.setFont(new Font("Arial", 16));
		y.setLayoutX(20);
		y.setLayoutY(595);
		
		
		
		//Choicebox
		xGegner.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"));
		xGegner.setLayoutX(125);
		xGegner.setLayoutY(550);
		xGegner.setDisable(true);
		
		yGegner.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));
		yGegner.setLayoutX(120);
		yGegner.setLayoutY(590);
		yGegner.setDisable(true);
		
		submit2.setPrefSize(180, 30);
		submit2.setLayoutX(10);
		submit2.setLayoutY(640);
		submit2.setOnAction(event->con.submit2());
		submit2.setDisable(true);
		
		turn.setLayoutX(60);
		turn.setLayoutY(680);
		turn.setFont(new Font("Arial", 18));
		
		pane1.getChildren().addAll(turn, input, feld, send, an, x, y, xGegner, yGegner, submit2);
		primaryStage.setWidth(1630);
		primaryStage.setHeight(730);
		primaryStage.centerOnScreen();
		
	}
	
	public Pane getPane1() {
		return pane1;
	}


	public void setPane1(Pane pane1) {
		this.pane1 = pane1;
	}


	public TextArea getInput() {
		return input;
	}


	public void setInput(TextArea input) {
		this.input = input;
	}


	public TextField getFeld() {
		return feld;
	}


	public void setFeld(TextField feld) {
		this.feld = feld;
	}


	public Button getSend() {
		return send;
	}


	public void setSend(Button send) {
		this.send = send;
	}


	public Label getAn() {
		return an;
	}


	public void setAn(Label an) {
		this.an = an;
	}


	public Label getX() {
		return x;
	}


	public void setX(Label x) {
		this.x = x;
	}


	public Label getY() {
		return y;
	}


	public void setY(Label y) {
		this.y = y;
	}


	public ChoiceBox<String> getxGegner() {
		return xGegner;
	}


	public void setxGegner(ChoiceBox<String> xGegner) {
		this.xGegner = xGegner;
	}


	public ChoiceBox<Integer> getyGegner() {
		return yGegner;
	}


	public void setyGegner(ChoiceBox<Integer> yGegner) {
		this.yGegner = yGegner;
	}


	public Button getSubmit2() {
		return submit2;
	}


	public void setSubmit2(Button submit2) {
		this.submit2 = submit2;
	}


	public Button getClear() {
		return clear;
	}


	public void setClear(Button clear) {
		this.clear = clear;
	}


	public Button getSubmit() {
		return submit;
	}


	public void setSubmit(Button submit) {
		this.submit = submit;
	}


	public Label getAchtung() {
		return achtung;
	}


	public void setAchtung(Label achtung) {
		this.achtung = achtung;
	}


	public Button getOk() {
		return ok;
	}


	public void setOk(Button ok) {
		this.ok = ok;
	}


	public Controller getCon() {
		return con;
	}


	public void setCon(Controller con) {
		this.con = con;
	}


	public Model getMod() {
		return mod;
	}


	public void setMod(Model mod) {
		this.mod = mod;
	}


	public Stage getPrimaryStage() {
		return primaryStage;
	}


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public Button getA() {
		return a;
	}


	public void setA(Button a) {
		this.a = a;
	}


	public Button getB() {
		return b;
	}


	public void setB(Button b) {
		this.b = b;
	}


	public Button getC() {
		return c;
	}


	public void setC(Button c) {
		this.c = c;
	}


	public Button getD() {
		return d;
	}


	public void setD(Button d) {
		this.d = d;
	}


	public Pane getPane() {
		return pane;
	}


	public void setPane(Pane pane) {
		this.pane = pane;
	}


	public Label getLposition() {
		return lposition;
	}


	public void setLposition(Label lposition) {
		this.lposition = lposition;
	}


	public GridPane getGrid() {
		return grid;
	}


	public void setGrid(GridPane grid) {
		this.grid = grid;
	}


	public Label getOrientierung() {
		return orientierung;
	}


	public void setOrientierung(Label orientierung) {
		this.orientierung = orientierung;
	}


	public ToggleGroup getGroup() {
		return group;
	}


	public void setGroup(ToggleGroup group) {
		this.group = group;
	}


	public RadioButton getRb1() {
		return rb1;
	}


	public void setRb1(RadioButton rb1) {
		this.rb1 = rb1;
	}


	public RadioButton getRb2() {
		return rb2;
	}


	public void setRb2(RadioButton rb2) {
		this.rb2 = rb2;
	}


	public ChoiceBox<String> getxKoordinateAnfang() {
		return xKoordinateAnfang;
	}


	public void setxKoordinateAnfang(ChoiceBox<String> xKoordinateAnfang) {
		this.xKoordinateAnfang = xKoordinateAnfang;
	}


	public ChoiceBox<Integer> getyKoordinateAnfang() {
		return yKoordinateAnfang;
	}


	public void setyKoordinateAnfang(ChoiceBox<Integer> yKoordinateAnfang) {
		this.yKoordinateAnfang = yKoordinateAnfang;
	}




	public Label getxKanfang() {
		return xKanfang;
	}


	public void setxKanfang(Label xKanfang) {
		this.xKanfang = xKanfang;
	}


	public Label getyKanfang() {
		return yKanfang;
	}


	public void setyKanfang(Label yKanfang) {
		this.yKanfang = yKanfang;
	}


	public Label getAnfang() {
		return anfang;
	}


	public void setAnfang(Label anfang) {
		this.anfang = anfang;
	}


	public Label getEnde() {
		return orientierung;
	}


	public void setEnde(Label ende) {
		this.orientierung = ende;
	}	
	
	public void setTurnText(String s){
		turn.setText(s);
	}
	

}
