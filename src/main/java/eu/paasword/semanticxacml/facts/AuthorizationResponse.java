package eu.paasword.semanticxacml.facts;

public class AuthorizationResponse {

    private Request request;    
    private AuthorizationResultType result;
            
    public AuthorizationResponse(AuthorizationResultType result) {
        this.result = result;
    }

    public AuthorizationResultType getResult() {
        return result;
    }

    public void setResult(AuthorizationResultType result) {
        this.result = result;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    
    @Override
    public String toString() {
        return "AuthorizationResponse: { result=" + result + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizationResponse that = (AuthorizationResponse) o;
        return this.result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return result.hashCode();
    }
}
