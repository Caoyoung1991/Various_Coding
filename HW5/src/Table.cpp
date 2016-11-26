/*
 * Table.cpp
 *
 *  Created on: 2016年11月19日
 *      Author: YangCao
 */

// Name:
// Loginid:
// CSCI 455 PA5
// Fall 2016

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>
#include <algorithm>

// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"

//*************************************************************************

Table::Table() {
	hashSize = HASH_SIZE;
	data = new ListType[hashSize](); //fixed size
	numOfEntry = 0;
	track = new int[HASH_SIZE]();
}


Table::Table(unsigned int hSize) {
	hashSize = hSize;
	data = new ListType[hashSize](); //dynamic size
	numOfEntry = 0;
	track = new int[hashSize]();
}


int * Table::lookup(const string &key) {
	int index = hashCode(key);
	ListType res = listLookup(data[index], key);
	if(NULL == res)
		return NULL;
	else
		return &res->value;
}

bool Table::remove(const string &key) {
	int index = hashCode(key);
	bool res = listRemove(data[index], key);
	if(true == res){
		numOfEntry --;
		track[index] --;
	}
	return res;
}

bool Table::insert(const string &key, int value) {
	int index = hashCode(key);
	bool res = listAddLast(data[index], new Node(key, value));
	if(true == res){
		numOfEntry ++;
		track[index] ++;
	}
	return res;
}

int Table::numEntries() const {

	return numOfEntry;
}

unsigned int Table::countBuckets(int * track) const{
	int i = 0;
	int nonEtyBuckets = 0;
	while(i < hashSize){
			if(track[i] != 0)
				nonEtyBuckets ++;
			i++;
	}
	return nonEtyBuckets;
}

unsigned int Table::maxChainLength(int * track) const{
	int maxChain = 0;
	for(int i = 0; i < hashSize ; i++){
		maxChain = max(maxChain, track[i]);
	}
	return maxChain;
}


void Table::printAll() const {
	int i = 0;
	while(i < hashSize){
		if(data[i] != NULL)
			printList(data[i]);
		i++;
	}
}

void Table::hashStats(ostream &out) const {
	out << "number of buckets:" << " " << hashSize   << endl;
	out << "number of entries:" << " " << numOfEntry << endl;
	out << "number of non-empty buckets:" << " " << countBuckets(track) << endl;
	out << "longest chain:" << " " << maxChainLength(track) << endl;
}


// add definitions for your private methods here


