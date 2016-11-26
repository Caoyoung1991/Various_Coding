// Name:
// Loginid:
// CSCI 455 PA5
// Fall 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}




//*************************************************************************
// put the function definitions for your list functions below
bool listRemove(ListType & list, string key){
	ListType tmp = list;
	if(NULL == list) // nothing to remove;
		return false;
	if(list->key == key){ // remove the head of the list
		list = tmp->next;
		return true;
	}
	while(NULL != tmp->next){ // remove any node except head
		if(key == tmp->next->key){
			if(NULL != tmp->next->next)
				tmp->next = tmp->next->next;
			else
				tmp->next = NULL;
			return true;
		}else{
			tmp = tmp->next;
		}
	}
	return false;
}

bool listAddFirst(ListType & list, Node * node){
	ListType cur = listLookup(list, node->key);
	if(NULL != cur)
		return false;
	else{
		if(NULL == list)
			list = node;
		else{
			ListType tmp = list->next;
			list->next = node;
			node->next = tmp;
		}
		return true;
	}
}

bool listAddLast(ListType & list, Node* node){
	ListType cur = listLookup(list, node->key);
	if(NULL != cur){
		return false;
	}else{
		if(NULL != listGetLast(list))
			listGetLast(list)->next = node;
		else
			list = node;
	}
	return true;
}

ListType listLookup(ListType & list, string key){
	if(NULL == list)
		return NULL;
	ListType tmp = list;
	while(tmp != NULL){
		if(tmp->key == key){
			return tmp;
		}
		tmp = tmp->next;
	}
	return NULL;
}

ListType listGetLast(ListType & list){
	if(NULL == list)
		return NULL;
	ListType tmp = list;
	while(tmp->next != NULL){
		tmp = tmp->next;
	}
	return tmp;
}

void printList(ListType & list){
	if(NULL == list)
		return ;
	ListType tmp = list;
	while(NULL != tmp ){
		cout << tmp->key << "  " << tmp->value << endl;
		tmp = tmp->next;
	}
	return ;
}

