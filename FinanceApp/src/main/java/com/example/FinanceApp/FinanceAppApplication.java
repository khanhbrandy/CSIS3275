package com.example.FinanceApp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.FinanceApp.model.Goal;
import com.example.FinanceApp.model.GoalRepository;
import com.example.FinanceApp.model.Instruction;
import com.example.FinanceApp.model.InstructionRepository;
import com.example.FinanceApp.model.Reminder;
import com.example.FinanceApp.model.ReminderRepository;
import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.CustomerRepository;
import com.example.FinanceApp.model.Transaction;

@SpringBootApplication
public class FinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
	}

	@Bean // right after calling Main method (server started), this Bean method would be
			// called automatically
	ApplicationRunner init(CustomerRepository customerRepo, GoalRepository goalRepository,
			InstructionRepository instructionRepo, ReminderRepository reminderRepo) {
		return (arg) -> {
			// Customer 1
			Customer customer1 = new Customer("Brandy", 30, "brandy@douglascollege.ca", "Student", "Vietnam", "brandy",
					"123456");
			Transaction transaction1 = new Transaction(60, "food", "expense", "at Walmart");
			customer1.getTransactions().add(transaction1);
			transaction1.setCustomer(customer1);
			Transaction transaction2 = new Transaction(2000, "salary", "income", "side-business");
			customer1.getTransactions().add(transaction2);
			transaction2.setCustomer(customer1);
			customerRepo.save(customer1);

			// Customer 2
			Customer customer2 = new Customer("Juan", 30, "juan@douglascollege.ca", "Student", "Columbia", "juan",
					"123456");
			Transaction transaction3 = new Transaction(140, "clothing", "expense", "at Zara");
			customer2.getTransactions().add(transaction3);
			transaction3.setCustomer(customer2);
			Transaction transaction4 = new Transaction(750, "housing", "expense", "rent");
			customer2.getTransactions().add(transaction4);
			transaction4.setCustomer(customer2);
			customerRepo.save(customer2);
			Transaction transaction5 = new Transaction(2500, "salary", "income", "part-time job");
			customer2.getTransactions().add(transaction5);
			transaction5.setCustomer(customer2);
			customerRepo.save(customer2);

			// Customer 3
			Customer customer3 = new Customer("Lara", 37, "lara@douglascollege.ca", "Student", "Canada", "lara",
					"123456");
			Transaction transaction6 = new Transaction(90, "food", "expense", "at Save-on");
			customer3.getTransactions().add(transaction6);
			transaction6.setCustomer(customer3);
			Transaction transaction7 = new Transaction(780, "housing", "expense", "rent");
			customer3.getTransactions().add(transaction7);
			transaction7.setCustomer(customer3);
			customerRepo.save(customer3);
			Transaction transaction8 = new Transaction(3000, "investment", "income", "real estate");
			customer3.getTransactions().add(transaction8);
			transaction8.setCustomer(customer3);
			customerRepo.save(customer3);

			// Customer 4
			Customer customer4 = new Customer("Akash", 28, "alan@douglascollege.ca", "Student", "India", "alan",
					"123456");
			Transaction transaction9 = new Transaction(66, "food", "expense", "at TnT");
			customer4.getTransactions().add(transaction9);
			transaction9.setCustomer(customer4);
			Transaction transaction10 = new Transaction(2800, "salary", "income", "freelance");
			customer4.getTransactions().add(transaction10);
			transaction10.setCustomer(customer4);
			customerRepo.save(customer4);

			// Some test Goals
			Set<Goal> goalsCustomer1 = new HashSet<>();
			Set<Goal> goalsCustomer2 = new HashSet<>();
			Goal goal1 = new Goal("House", 200000, 70000, "Money for my house", "2025-12-03");
			goal1.setCustomer(customer1);
			Goal goal2 = new Goal("Car", 65000, 8000, "For my dream Car", "2024-05-30");
			goal2.setCustomer(customer1);
			goalsCustomer1.add(goal1);
			goalsCustomer1.add(goal2);
			customer1.setGoal(goalsCustomer1);

			Goal goal3 = new Goal("House", 500000, 100000, "Money for my house", "2039-03-01");
			goal3.setCustomer(customer2);
			goalsCustomer2.add(goal3);
			customer2.setGoal(goalsCustomer2);

			goalRepository.save(goal1);
			goalRepository.save(goal2);
			goalRepository.save(goal3);
			goalRepository.findAll().forEach(System.out::println);

			// Adding instructions to the database
			String engIns1 = "1. The user needs to do registration first to use the application.";
			String engIns2 = "2. On registration , the user must login with the username and password.";
			String engIns3 = "3. To add a transaction you must click on transaction tab and fill all the requred fields .";
			String engIns4 = "4. To set financial Goals, click on Goals and add required fields to add Goals.";
			String engIns5 = "5. View Goals button help you seeing all the goals.";
			String engIns6 = "6. Reminder section is to add reminders for future transactions. To add reminders you need to fill the required fields and add reminder.";
			String engIns7 = "7. View Reminders button list down all the reminders which are pending or completed.";
			String engIns8 = "8. Reports section is used to see your overall transactions summary, how much the amount have been credited or debited so far.It gives a graph representation of transaction summary.";
			
			String spaIns1 = "1. El usuario debe registrarse primero para usar la aplicación.";
			String spaIns2 = "2. En el momento del registro, el usuario deberá iniciar sesión con el nombre de usuario y la contraseña.";
			String spaIns3 = "3. Para agregar una transacción, debe hacer clic en la pestaña de transacción y completar todos los campos requeridos.";
			String spaIns4 = "4. Para establecer objetivos financieros, haga clic en Objetivos y agregue los campos obligatorios para agregar objetivos.";
			String spaIns5 = "5. El botón Ver objetivos lo ayuda a ver todos los objetivos.";
			String spaIns6 = "6. La sección de recordatorios es para agregar recordatorios para transacciones futuras. Para agregar recordatorios, debe completar los campos obligatorios y agregar un recordatorio.";
			String spaIns7 = "7. El botón Ver recordatorios enumera todos los recordatorios que están pendientes o completados.";
			String spaIns8 = "8. La sección de informes se utiliza para ver el resumen general de sus transacciones, cuánto se ha acreditado o debitado hasta el momento. Ofrece una representación gráfica del resumen de la transacción.\n"
					+ "";

			String freIns1 = "1. L'utilisateur doit d'abord s'enregistrer pour utiliser l'application.";
			String freIns2 = "2. Lors de l'inscription, l'utilisateur doit se connecter avec le nom d'utilisateur et le mot de passe.\n"
					+ "";
			String freIns3 = "3. Pour ajouter une transaction, vous devez cliquer sur l'onglet transaction et remplir tous les champs obligatoires.";
			String freIns4 = "4. Pour définir des objectifs financiers, cliquez sur Objectifs et ajoutez les champs requis pour ajouter des objectifs.";
			String freIns5 = "5. Le bouton Afficher les objectifs vous aide à voir tous les objectifs.";
			String freIns6 = "6. Reminder permet d'ajouter des rappels pour les transactions futures. Pour ajouter des rappels, vous devez remplir les champs obligatoires et ajouter un rappel.";
			String freIns7 = "7. Le bouton Afficher les rappels répertorie tous les rappels en attente ou terminés.";
			String freIns8 = "8. La section Rapports est utilisée pour voir le résumé global de vos transactions, combien le montant a été crédité ou débité jusqu'à présent. Il donne une représentation graphique du résumé des transactions.";

			ArrayList<String> instructions1 = new ArrayList<String>();
			instructions1.add(engIns1);
			instructions1.add(engIns2);
			instructions1.add(engIns3);
			instructions1.add(engIns4);
			instructions1.add(engIns5);
			instructions1.add(engIns6);
			instructions1.add(engIns7);
			instructions1.add(engIns8);

			ArrayList<String> instructions2 = new ArrayList<String>();
			instructions2.add(spaIns1);
			instructions2.add(spaIns2);
			instructions2.add(spaIns3);
			instructions2.add(spaIns4);
			instructions2.add(spaIns5);
			instructions2.add(spaIns6);
			instructions2.add(spaIns7);
			instructions2.add(spaIns8);

			ArrayList<String> instructions3 = new ArrayList<String>();
			instructions3.add(freIns1);
			instructions3.add(freIns2);
			instructions3.add(freIns3);
			instructions3.add(freIns4);
			instructions3.add(freIns5);
			instructions3.add(freIns6);
			instructions3.add(freIns7);
			instructions3.add(freIns8);

			Instruction instruction1 = new Instruction(instructions1, "english");
			Instruction instruction2 = new Instruction(instructions2, "spanish");
			Instruction instruction3 = new Instruction(instructions3, "french");

			instructionRepo.save(instruction1);
			instructionRepo.save(instruction2);
			instructionRepo.save(instruction3);

			// Adding Sample Reminders to table
			Reminder reminder1 = new Reminder("AppleCare payment", "Description......","100", "20230310", "20230320",
					"monthly", "pending");
			reminder1.setCustomer(customer1);
			Reminder reminder2 = new Reminder("Hydro payment", "Description......", "100","20230310", "20230320", "monthly",
					"pending");
			reminder2.setCustomer(customer1);
			Reminder reminder3 = new Reminder("Wifi payment", "Description......", "100","20230310", "20230320", "monthly",
					"pending");
			reminder3.setCustomer(customer1);
			Reminder reminder4 = new Reminder("Phone payment", "Description......","100", "20230310", "20230312", "monthly",
					"done");
			reminder4.setCustomer(customer1);
			Reminder reminder5 = new Reminder("Network Provider payment", "Description......","100", "20230310", "20230312",
					"monthly", "done");
			reminder5.setCustomer(customer1);
			
			Set<Reminder> reminders = new HashSet<>();
			reminders.add(reminder1);
			reminders.add(reminder2);
			reminders.add(reminder3);
			reminders.add(reminder4);
			reminders.add(reminder5);
			
			customer1.setReminders(reminders);
			reminderRepo.save(reminder1);
			reminderRepo.save(reminder2);
			reminderRepo.save(reminder3);
			reminderRepo.save(reminder4);
			reminderRepo.save(reminder5);
		};
	}

}
