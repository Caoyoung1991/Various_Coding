// Name:
// Loginid:
// CSCI 455 PA5
// Fall 2016

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 *
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>
#include <sstream>


/*
 * insert a new student to the table, if the student is already
 * exist, don not insert.
 */
void insert(string name, int score, Table * grades){
	if(false == grades->insert(name, score))
			  cout << name << " was already present." << endl;
}

/*
 * if the student is on the table,change a student's score.
 * else, print the information.
 */
void change(string name, int score, Table * grades){
	if(false == grades->remove(name))
		cout << name << " wasn't in the grade table." << endl;
	else
		grades->insert(name, score);
}

/*
 * look for a student in the table and print his/her score.
 */
void lookup(string name, Table * grades){
	int * v = grades->lookup(name);
	if(NULL != v)
	  cout << name << " " << *v << endl;
	else
	  cout << name << "is not in the table." << endl;
}

/*
 * remove a student in the table if he/she was already in the table.
 */
void remove(string name, Table * grades){
	if(false == grades->remove(name))
		cout << name << " wasn't in the grade table." << endl;
}

/*
 * print all students' information in the table.
 */
void print(Table * grades){
	grades->printAll();
}

/*
 * print number of entries in the table.
 */
void size(Table * grades){
	cout << grades->numEntries() << endl;
}

/*
 * print the current stats of the table.
 */
void stats(Table * grades){
	grades->hashStats(cerr);
}

/*
 * print help information.
 */
void help(){
	cout << "help" << endl;
}

/*
 * exit the program.
 */
void quit(Table * grades){
	delete grades;
	exit(0);
}

/*
 * execute all the commands.
 */
void executeCmd(istringstream &cmd, Table* grades){
	string method;
	string name;
	int score;
	cmd >> method;
	cmd >> name;
	cmd >> score;

	if("insert" == method) {
		insert(name, score, grades);
	}else if("change" == method){
		change(name, score, grades);
	}else if("lookup" == method){
		lookup(name, grades);
	}else if("remove" == method){
		remove(name,grades);
	}else if("print"  == method){
		print(grades);
	}else if("size"   == method){
		size(grades);
	}else if("stats"  == method){
		stats(grades);
	}else if("help"   == method){
		help();
	}else if("quit"   == method){
		quit(grades);
	}else{
	  cout << "invalid command" << endl;
	}
}



int main(int argc, char * argv[]) {

  // gets the hash table size from the command line

  int hashSize = Table::HASH_SIZE;
  Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.
  if (argc > 1) {
	  hashSize = atoi(argv[1]);  // atoi converts c-string to int

	  if (hashSize < 1) {
		  cout << "Command line argument (hashSize) must be a positive number"<< endl;
		  return 1;
	  }
	  grades = new Table(hashSize);
  }
  else {   // no command line args given -- use default table size
	  grades = new Table();
  }
  grades->hashStats(cout);
  // add more code here
  // Reminder: use -> when calling Table methods, since grades is type Table*

  while(true){
	cout << "cmd>"; // cmd> promt sign
	string cmd;
	getline(cin, cmd); // get command line.
	istringstream in(cmd); // convert command into input stream.
	executeCmd(in, grades); // execute the command.
  }
  return 0;
}

