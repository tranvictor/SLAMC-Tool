package model;

import java.util.List;

import cern.colt.map.OpenIntObjectHashMap;

public class TrieNode<AbstractSememe> {
	private OpenIntObjectHashMap childrenReference;
	private TrieNode<AbstractSememe> parent;
	private AbstractSememe data;
	private int occurrence; // number of occurrences
	
	private void init(){
		parent = null;
		occurrence = 0;
	}
	
	// Constructor for root of the tree
	protected TrieNode(){
		init();
		childrenReference = new OpenIntObjectHashMap();
	}
	
	protected TrieNode(AbstractSememe value, TrieNode<AbstractSememe> p){
		init();
		this.parent = p;
		this.data = value;
		this.parent.addChild(this);
	}

	@SuppressWarnings("unchecked")
	protected TrieNode<AbstractSememe> addChild(TrieNode<AbstractSememe> trieNode) {
		TrieNode<AbstractSememe> child = (TrieNode<AbstractSememe>)childrenReference.get(trieNode.hashCode());
		if (child == null){
			childrenReference.put(trieNode.hashCode(), trieNode);
		} else {
			child.occurrence++;
		}
		return child;
	}
	
	@SuppressWarnings("unchecked")
	protected TrieNode<AbstractSememe> addChildBySememe(AbstractSememe sememe) {
		TrieNode<AbstractSememe> child = (TrieNode<AbstractSememe>)childrenReference.get(sememe.hashCode());
		if (child == null){
			childrenReference.put(sememe.hashCode(), new TrieNode<AbstractSememe>(sememe, this));
		} else {
			child.occurrence++;
		}
		return child;
	}
	
	protected void addSequence(List<AbstractSememe> sequence){
		TrieNode<AbstractSememe> current = this;
		for (AbstractSememe sememe : sequence){
			current = current.addChildBySememe(sememe);
		}
	}
	
	protected int getOccurrence(){
		return this.occurrence;
	}
	
	public int hashCode(){
		return this.data.hashCode();
	}
}
