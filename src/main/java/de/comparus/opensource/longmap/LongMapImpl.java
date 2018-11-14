package de.comparus.opensource.longmap;

import java.util.Arrays;

/**
 * LongMapImpl implements LongMap interface
 * 
 * @author Artem Meleshko
 * @version 1.0.1
 * @param <V> Generic
 */
public class LongMapImpl<V> implements LongMap<V> {
	
	float loadFactor = 0.75f;
	private int TABLE_SIZE = 16;
	private int counter = 0;
	private Entry<V>[] table = new Entry[TABLE_SIZE];
	
	static class Entry<V> {
        Long key;
        V value;
        Entry<V> next;
    
        public Entry(long key, V value, Entry<V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
	
	/**
	 * LongMapImpl empty constructor
	 */
	public LongMapImpl() {};
	
	public V put(long key, V value) {
		int hash = hash(key);
		Entry<V> entry = new Entry<V>(key, value, null);
		if(table[hash] == null){
	         table[hash] = entry;
	        }else{
	           Entry<V> previous = null;
	           Entry<V> current = table[hash];
	           while(current != null){ 
	               if(current.key.equals(key)){           
	                   if(previous==null){  
	                         entry.next=current.next;
	                         table[hash]=entry;
	                         return entry.value;
	                   }
	                   else{
	                       entry.next=current.next;
	                       previous.next=entry;
	                       return null;
	                   }
	               }
	               previous=current;
	                 current = current.next;
	             }
	             previous.next = entry;
	            }
		if(counter>TABLE_SIZE*loadFactor) {
 			TABLE_SIZE = (int) (TABLE_SIZE*1.5f);
 			System.out.println(TABLE_SIZE);
 			table = Arrays.copyOf(table, TABLE_SIZE);
 		}
		counter++;
		return null;
	}

	public V get(long key) {
		int hash = hash(key);
        if(table[hash] == null){
         return null;
        }else{
         Entry<V> temp = table[hash];
         while(temp!= null){
             if(temp.key.equals(key))
                 return temp.value;
             temp = temp.next; 
         }         
         return null;   
        }
	}

	public V remove(long key) {
		int hash=hash(key);
        
	      if(table[hash] == null){
	            return null;
	      }else{
	        Entry<V> previous = null;
	        Entry<V> current = table[hash];
	        
	        while(current != null){ 
	           if(current.key.equals(key)){               
	               if(previous==null){  
	                     table[hash]=table[hash].next;
	                     counter--;
	                     return null;
	               }
	               else{
	                     previous.next=current.next;
	                      return null;
	               }
	           }
	           previous=current;
	             current = current.next;
	          }
	        return null;
	      }
	}

	public boolean isEmpty() {
		int counter = 0;
		 for(int i=0;i<table.length;i++) {
			 if(table[i] != null){
		            counter++;
		      }
		 }
		 if (counter > 0) {return false;
		 }else return true;
		
	}

	public boolean containsKey(long key) {
		int hash = hash(key);
        if(table[hash] == null){
         return false;
        }else{
         Entry<V> temp = table[hash];
         while(temp!= null){
             if(temp.key.equals(key))
                 return true;
             temp = temp.next;
         }         
         return false;   
        }
	}

	public boolean containsValue(V value) {
		for(int i=0;i<table.length;i++) {
			 if(table[i] != null){
				 Entry<V> temp = table[i];
		         while(temp!= null){
		             if(temp.value.equals(value))
		                 return true;
		             temp = temp.next;
		         }  
		      }
		}
		return false;
	}

	public long[] keys() {
		int cont = 0;
		long[] longKeys = new long[counter];
		for(int i=0;i<table.length;i++) {
			 if(table[i] != null){
		            Entry<V> temp = table[i];
		            while(temp!= null) {
		            	longKeys[cont] = temp.key;
		            	cont++;
		            	temp = temp.next;
		            }
		      }
		}
		return longKeys;
	}

	public V[] values() {
		int cont = 0;
		
		@SuppressWarnings("unchecked")
		 V[] arrayValues =(V[]) new Object[counter];
		for(int i=0;i<table.length;i++) {
			if(table[i] != null){
	            Entry<V> temp = table[i];
	            while(temp!= null) {
	            	arrayValues[cont] = temp.value;
	            	cont++;
	                temp = temp.next;
	            }
	      }
		}
		return arrayValues;
	}

	public long size() {
		return TABLE_SIZE;
	}

	public void clear() {
		for(int i=0;i<table.length;i++) {
			table[i]=null;
		}
	}
	
	private int hash(Long key){
		return Math.abs(key.hashCode()) % TABLE_SIZE-1;
    }
	
    public void display(){
        
        for(int i=0;i<TABLE_SIZE;i++){
            if(table[i]!=null){
                   Entry<V> entry=table[i];
                   while(entry!=null){
                         System.out.print("{"+entry.key+"="+entry.value+"}" +" ");
                         entry=entry.next;
                   }
            }
        }
    }
	
}