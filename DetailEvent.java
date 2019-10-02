import java.util.EventObject;

public class DetailEvent extends EventObject{

    private Node node;

    public DetailEvent(Object source, int x, int y) {
        super(source);
        this.node = new Node();
        node.shape.x = x;
        node.shape.x = y;
    }

    public Node getNode(){
        return node;
    }
    
}