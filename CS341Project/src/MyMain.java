public class MyMain {

	public static void main(String[] args) {

		// TASK 1: CREATE A CANVAS FOR ANIMATION
		Canvas canvas = new Canvas();
		canvas.requestFocus();
		
		//TASK 2:  ADD A USER GAME OBJECT
		
		Type_A_GameObject user1 = new Type_A_GameObject(100, 100);
		user1.setVelocity(10);
		canvas.addKeyListener(user1);
		canvas.addGameObject(user1);
		
		Type_B_GameObject user2 = new Type_B_GameObject(150, 150);
		user2.setVelocity(10);
		canvas.addKeyListener(user2);
		canvas.addGameObject(user2);

		Type_C_GameObject user3 = new Type_C_GameObject(50, 50);
		user3.setVelocity(10);
		canvas.addKeyListener(user3);
		canvas.addGameObject(user3);
		
		Type_D_GameObject user = new Type_D_GameObject(200, 200);
		user.setVelocity(10);
		canvas.addKeyListener(user);
		canvas.addGameObject(user);


	}

}

