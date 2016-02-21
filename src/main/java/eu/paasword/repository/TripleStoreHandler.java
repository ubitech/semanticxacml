/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paasword.repository;

import java.util.ArrayList;
import java.util.List;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.http.HTTPRepository;

/**
 *
 * @author eleni
 */
public class TripleStoreHandler {

//    public static void main(String[] args) {
//        List<Value> retlist = TripleStoreHandler.queryRepository();
//        for (Value value : retlist) {
//            System.out.println(value);
//        }
//    }

    public static List<Value> queryRepository() {
        ArrayList retlist = new ArrayList();
        
        String sesameServer = "http://192.168.8.96:8080/openrdf-sesame/";
        String repositoryID = "test";

        try {
            Repository repo = new HTTPRepository(sesameServer, repositoryID);
            repo.initialize();
            RepositoryConnection con = repo.getConnection();

            try {
                String queryString = " PREFIX ex: <http://example#>  SELECT   ?x  WHERE {   ex:r111 rdfs:subClassOf ?x.}";
                TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

                TupleQueryResult result = tupleQuery.evaluate();
                try {
                    while (result.hasNext()) {  // iterate over the result
                        BindingSet bindingSet = result.next();
                        Value valueOfX = bindingSet.getValue("x");
                        // do something interesting with the values here...
                        retlist.add(valueOfX);
//                        System.out.println(valueOfX);
                    }
                } finally {
                    result.close();
                }
            } finally {
                con.close();
            }
        } catch (OpenRDFException e) {
            e.printStackTrace();
        }
        return retlist;
    }//EoC

}
