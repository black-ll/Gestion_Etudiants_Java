package interfaceEtudiant;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfaceEtudiant extends JFrame {

	private JPanel contentPane;
	private JTextField nomInput;
	private JTextField prenomInput;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable tableEtudiants;
	private DefaultTableModel model;
	private String sexe;
	
	private EtudiantService etudiants = new EtudiantService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceEtudiant frame = new InterfaceEtudiant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceEtudiant() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informations Etudiant", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 13, 449, 151);
		contentPane.add(panel);
		panel.setLayout(null);
		
		nomInput = new JTextField();
		nomInput.setBounds(70, 41, 116, 22);
		panel.add(nomInput);
		nomInput.setColumns(10);
		
		prenomInput = new JTextField();
		prenomInput.setColumns(10);
		prenomInput.setBounds(70, 90, 116, 22);
		panel.add(prenomInput);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(2, 44, 56, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_1.setBounds(12, 93, 56, 16);
		panel.add(lblNewLabel_1);
		
		JRadioButton feminin = new JRadioButton("F\u00E9minin");
		buttonGroup.add(feminin);
		feminin.setBounds(263, 41, 80, 25);
		panel.add(feminin);
		
		JRadioButton masculin = new JRadioButton("Masculin");
		buttonGroup.add(masculin);
		masculin.setBounds(347, 41, 80, 25);
		panel.add(masculin);
		
		JLabel lblNewLabel_2 = new JLabel("Sexe :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(199, 44, 56, 16);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBoxFiliere = new JComboBox();
		comboBoxFiliere.setBounds(315, 90, 100, 22);
		panel.add(comboBoxFiliere);
		comboBoxFiliere.setModel(new DefaultComboBoxModel<String>(new String[] {"Technique", "Scientifique", "Littéraire", "Informatique", "Architecture"}));
		
		JLabel lblNewLabel_3 = new JLabel("Fili\u00E8re :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(234, 93, 56, 16);
		panel.add(lblNewLabel_3);
		
		
		// Bouton add 
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (nomInput.getText().isEmpty() || prenomInput.getText().isEmpty()) 
				{
					// msg d'alerte pour l'utilisateur
					JOptionPane.showMessageDialog(contentPane,"Merci d'indiquer un nom et un prénom");
				}
				
				else
				{
				
					// pr récupérer la sélection du sexe
					if (feminin.isSelected())
						sexe = "Féminin";
					else
						sexe = "Masculin";
					// on créée un nouvel objet "Etudiant" que l'on instancie avec le constructor 
					Etudiant et = new Etudiant(nomInput.getText(), prenomInput.getText(), sexe , comboBoxFiliere.getSelectedItem() + "");
					
					// on ajoute une nouvelle ligne avec les infos du tableau d'objet défini 
					model.insertRow(model.getRowCount(), new Object[] {et.getId(), et.getNom(), et.getPrenom(), et.getSexe(), et.getFiliere()});
					
					// on ajoute l'étudiant ds l'arrayList etudiants
					etudiants.create(et);
					System.out.println(etudiants);
					
					// on vide les champs
					nomInput.setText("");
					prenomInput.setText("");
					
					buttonGroup.clearSelection();
				}
				
			}
		});
		btnNewButton.setBounds(508, 130, 97, 25);
		contentPane.add(btnNewButton);
		
		// Bouton supprimmer
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// On récupère les valeurs de la colonne id ds la var id
				int id = Integer.parseInt(tableEtudiants.getValueAt(tableEtudiants.getSelectedRow(), 0) + "");
				
				// on supprimme la ligne de notre EtudiantService etudiants
				etudiants.delete(etudiants.findById(id));
				
				model.removeRow(tableEtudiants.getSelectedRow());
				
				// on remet les champs à 0
				nomInput.setText("");
				prenomInput.setText("");
				buttonGroup.clearSelection();
				
				System.out.println(etudiants);
			}
		});
		btnNewButton_1.setBounds(508, 201, 97, 25);
		contentPane.add(btnNewButton_1);
		
		// bouton modifier 
		JButton btnNewButton_2 = new JButton("Modifier");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// on modifie les valeurs de chaque cas en reprenant les éléments remplis
				model.setValueAt(nomInput.getText(), tableEtudiants.getSelectedRow(), 1);
				model.setValueAt(prenomInput.getText(), tableEtudiants.getSelectedRow(), 2);
				
				model.setValueAt(comboBoxFiliere.getSelectedItem(), tableEtudiants.getSelectedRow(), 4);
				
				if (feminin.isSelected())
					sexe = "Féminin";
				else
					sexe = "Masculin";
				model.setValueAt(sexe, tableEtudiants.getSelectedRow(), 3);
				
				// On récupère l'id qui se trouve ds la colonne 0
				int id = Integer.parseInt(tableEtudiants.getValueAt(tableEtudiants.getSelectedRow(), 0) + "");
				
				// on récupère les éléments de cet id dans la var etu pour modifier notre ArrayList 
				Etudiant etu = etudiants.findById(id);
				etu.setNom(nomInput.getText());
				etu.setPrenom(prenomInput.getText());
				etu.setFiliere(comboBoxFiliere.getSelectedItem() + "");
				
				if (feminin.isSelected())
					etu.setSexe("Féminin");
				else
					etu.setSexe("Masculin");
				
				// on met à jour l'arrayList
				etudiants.update(etu);
				
				// on remet les champs à 0
				nomInput.setText("");
				prenomInput.setText("");
				buttonGroup.clearSelection();
				
				// on ajoute un msg indiquant à l'utilisateur que la modif est bien prise en compte
				// JOptionPane.showMessageDialog(contentPane,"Modification bien effectuée");
				
				
				System.out.println(etudiants);
			}
		});
		btnNewButton_2.setBounds(508, 276, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Liste des \u00E9tudiants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 201, 449, 203);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 201, 445, 199);
		panel_1.add(scrollPane);
		
		
		tableEtudiants = new JTable();
		
		// Ajout method Mouse -> qd on clique sur la ligne du tableau, les données se remettent ds les inputs
		tableEtudiants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// on récupère les infos contenues dans la case "sexe" dans x
				String x = String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 3));
				
				// on affecte masculin ou feminin dans les JButtons selon le cas
				if (x =="Masculin")
				{
					masculin.setSelected(true);
				}
				else 
				{
					feminin.setSelected(true);
				}
				
				nomInput.setText(String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 1)));
				prenomInput.setText(String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 2)));
				
				comboBoxFiliere.setSelectedItem(String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 4)));
			}
		});
		
		// definition des entrées en entête
		tableEtudiants.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Pr\u00E9nom", "Sexe", "Fili\u00E8re"
			}
		));
		// on ajoute le tableau ds le scrollPane avec setView 
		scrollPane.setViewportView(tableEtudiants);
		
		// on récupère notre model de tableau pr s'en servir (model à déclarer ds les var au-dessus)
		model = (DefaultTableModel)tableEtudiants.getModel();
		
		
		
		
	}
}
