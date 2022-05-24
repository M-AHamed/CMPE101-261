#include "Main.h"
#include <iostream>
#include <stdlib.h>
#include <vector>
#include <iterator>
#include <string>
#include <algorithm>
using namespace std;

// Mohammad Hamed, 120200155

// functionality of the game:
// 2 players can play
// player vs computer
// display an interactive borad for the user

// flag to signal if the game is running or no
bool running = true;
// flags to indicate if the user/guest/cpu has played in the last turn
bool userPlayedLastTurn = false;
bool guestPlayedLastTurn = false;
bool cpuPlayedLastTurn = false;
// to identify which gameMode was chsoen
bool cpuVsUser = false;
bool userVsGuest = false;
// to identify if a player played this turn
bool someonePlayedThisTurn = false;
// stores the row selected by the user
int rowSelected = 0;
// stores the number of sticks to remove selected by the user
int numberOfSticks = 0; 
// 1 for singleplayer, 2 for multiplayer
int gameMode = 0; 
// number of elements in each row, representd as the total
int board[4] = { 1,3,5,7 }; 

// game funcitons:

// game menu function
int gameMenu() {
	// prints out the game menu
	// option1: play against computer
	// option2: play against other player:
	// option3: quit game
	int choice;
	cout << "------------------------------------------------NIM GAME------------------------------------------------" << endl; 
	cout << " welcome to the game of Nim, the goal is not to be the one to pick the last peace" << endl;
	cout << " choose one of the following game modes, choose, 1,2,3" << endl;
	cout << " option 1: play against the computer || option 2: play agianst another player || option 3: quit the game" << endl;
	cin >> choice; // save the user choice in choice
	cout << " You chose option number: " << choice << endl; // display the choice of the user
	return choice;
}

// function to print out the board:
void printBoard(int array[]) {

	cout << " game board: " << endl;
	for (int i = 0; i < 4; i++) { // iterate through the game board, and print out the number of sticks remaining for each row
		if (array[i] == 0) { cout << " " << "\n"; }
		if (array[i] == 1) { cout << " I " << "\n"; }
		if (array[i] == 2) { cout << " II " << "\n"; }
		if (array[i] == 3) { cout << " III " << "\n"; }
		if (array[i] == 4) { cout << " IIII " << "\n"; }
		if (array[i] == 5) { cout << " IIIII " << "\n"; }
		if (array[i] == 6) { cout << " IIIIII " << "\n"; }
		if (array[i] == 7) { cout << " IIIIIII " << "\n"; }
	}
}

// function for the user to remove elements from the borad
void removeElementsPlayer() {
	bool choiceMade = false; // to indicate if the user made a choice or not
	int rowNumber = 0; 
	int NumberOfSticks = 0;

	while (choiceMade == false) {
		// ask user for number of sticks to remove and row number to remove sticks from
		cout << "User: input the row to remove a stick from. choose rows: 0 to 3" << endl;
		cin >> rowNumber;
		cout << "User:input the number of sticks you want to take from row number: " << rowNumber << endl;
		cin >> NumberOfSticks;
	
		// removing the elements from the array:
		if (NumberOfSticks <= board[rowNumber]) { // if it is possible to remove wanted number of sticks
			board[rowNumber] = board[rowNumber] - NumberOfSticks; // removing x number of sticks as long as x <= array[i]
			choiceMade = true;
			cout << "User: you have successfully removed: " << NumberOfSticks << " sticks from row number: " << rowNumber << endl;
		}
		else cout << "User: your choice is not possible please choose again" <<endl;
	}
	int sum = 0;
	for (int i = 0; i < 4; i++) {
		sum = sum + board[i];
	}
	if (sum == 0) {
		choiceMade = true; // to exit the while loop, if the board is empty
		cout << "user has lost the game" << endl;
	}

}

// function to calculate the nimSum:
int calculateNimSum(int array[]) {
	int nimSum = 0;
	// xor all of the rows to find the nimSum of the rows
	nimSum = array[0] ^ array[1] ^ array[2] ^ array[3];
	return nimSum;
}

// for the cpu to remove elements from the board
void removeElementsCPU() {
	bool choiceMade = false;
	int currentNimSum = 0;
	// calculating the current NimSUm of the board:
	currentNimSum = calculateNimSum(board);
	// to win the game you have to finnish every move with a nim sum of 0
	// can only remove from one row at a time
	// loop throughout the board array and test random combinaitons for every row to see if one of them could generate a nim sum of zero
	
	// if the current nim sum is bigger than 0
	if (currentNimSum != 0) {
		// for every element in the board
		for (int i = 0; i < 4; i++) {
			// if the element(number, 0 to 7) xor current nim sum is smaller than the current element 
			if ((board[i] ^ currentNimSum) < board[i]) {
				// assing the xor of board[i] and the current nim sum in board[i]
					board[i] =board[i] ^ currentNimSum;
					cout << "computer has made a move" << endl; 
					// exit out of the loop
					break;
			}
		}
	}
	else if(currentNimSum == 0){
		// if the current nim sum is zero, computer is at a losing position,
		// make a move were you remove one stick
		// for loop throughout the board
		for (int i = 0; i < 4; i++) {
			// if the number of sticks is bigger than zero 
			// remove one stick
			if (board[i] > 0) {
				board[i] = board[i] - 1;
				cout << "computer has made a move" << endl;
				break;
			}
		}
		
	}


}


// for the guest to remvoe elements from the borad
void removeElementsGuest() {
	bool choiceMade = false; // to indicate if the guest made a choice or not
	int rowNumber = 0;
	int NumberOfSticks = 0;
	// setting the user played to false
	while (choiceMade == false) { // while no correct choice has been made
		// ask user for number of sticks to remove and row number to remove sticks from
		cout << "Guest: input the row to remove a stick from. choose rows: 0 to 3" << endl;
		cin >> rowNumber;
		cout << "Guest: input the number of sticks you want to take from row number: " << rowNumber << endl;
		cin >> NumberOfSticks;

		// removing the elements from the array:
		if (NumberOfSticks <= board[rowNumber]) { // if it is possible to remove wanted number of sticks
			board[rowNumber] = board[rowNumber] - NumberOfSticks; // removing x number of sticks as long as x <= array[i]
			choiceMade = true;
			cout << "Guest: you have successfully removed: " << NumberOfSticks << " sticks from row number: " << rowNumber << endl;
		}
		else cout << "Guest: your choice is not possible please choose again" << endl;
	}
	int sum = 0;
	for (int i = 0; i < 4; i++) {
		sum = sum + board[i];
	}
	if (sum == 0) {
		choiceMade = true; // to exit the while loop, if the board is empty
		cout << "Guest has lost the game" << endl;
	}
}

// Game Loop function:
void gameLoop() {
	int gameMenu(); // game menu with options to play against pc, or another player
	int gameMode = gameMenu();
	if (gameMode == 1) {
		cpuVsUser = true;
	}
	if (gameMode == 2) {
		userVsGuest = true;
	}
	if (gameMode == 3) {
		exit(0);
	}
	// turn number counter
	int turnNumber = 0;

	// to store who will go first:
	int startingChoice = 0;
	// asking user if they want to go first, or if they want the computer to go first:
	if (cpuVsUser == true) {
		cout << " to go first enter 1, to let the computer go first enter 2:" << endl;
		cin >> startingChoice;
		printBoard(board);
		if (startingChoice == 1) {
			turnNumber++;
			userPlayedLastTurn = true;
			cpuPlayedLastTurn = false;
			someonePlayedThisTurn = true;
			removeElementsPlayer();
		}
		if (startingChoice == 2) {
			turnNumber++;
			userPlayedLastTurn = false;
			cpuPlayedLastTurn = true;
			someonePlayedThisTurn = true;
			removeElementsCPU();
		}
	}
	// to store who will go first in game mode 2
	int startingChoiceGameMode2 = 0;
	// asking who wants to go first
	if (userVsGuest == true) {
		cout << " to go first enter 1, to let the guest go first enter 2:" << endl;
		cin >> startingChoiceGameMode2;
		printBoard(board);
		if (startingChoiceGameMode2 == 1) {
			turnNumber++;
			userPlayedLastTurn = true;
			guestPlayedLastTurn = false;
			someonePlayedThisTurn = true;
			removeElementsPlayer();
		}
		if (startingChoiceGameMode2 == 2) {
			guestPlayedLastTurn = true;
			userPlayedLastTurn = false;
			someonePlayedThisTurn = true;
			turnNumber++;
			removeElementsGuest();
		}
	}
	
	while (running == true) {
		someonePlayedThisTurn = false;
		// checking if the borad is empty:
		int sum1 = 0;
		for (int i = 0; i < 4; i++) {
			sum1 = sum1 + board[i];
		}
		if (sum1 == 0) {
			running = false; // to exit the while loop
		}
		turnNumber++; // increment turn number counter
		cout << " ----round Number: " << turnNumber << "----" <<endl;
		// check the game status:
		printBoard(board); // at each iteration print out the new board
		
		if (userPlayedLastTurn == false && someonePlayedThisTurn == false) { // user can only play if he hasnt played last turn
			// function for that allows the user to remove elements from the board
			userPlayedLastTurn = true;
			guestPlayedLastTurn = false;
			cpuPlayedLastTurn = false;
			someonePlayedThisTurn = true;
			removeElementsPlayer();
		}
		// if game mode 1 is selected & no one played in this turn
		if (cpuVsUser == true && someonePlayedThisTurn == false){
		if (cpuPlayedLastTurn == false) {
			cpuPlayedLastTurn = true; 
			userPlayedLastTurn = false;
			someonePlayedThisTurn = true;
			removeElementsCPU();
		}
	}

		// work in progress:
		if (userVsGuest == true && someonePlayedThisTurn == false) {
			if (guestPlayedLastTurn == false) { // if the guest didnt play last turn
				guestPlayedLastTurn = true; // set guest flag to true
				userPlayedLastTurn = false; // set user flag to false
				someonePlayedThisTurn = true; // set somone flag to true
				removeElementsGuest();
			}
		}

		// checking if the board is empty
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum = sum + board[i];
		}
		if (sum == 0) {
			running = false; // to exit the while loop
		}
	}
}

// main method:
int main() {
	gameLoop();
	cout << "------------------------------------------------GAME OVER------------------------------------------------" << endl; // output this losing message
	return 0;
}