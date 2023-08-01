package demo;

abstract class Entity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Abstract method for inserting entity data
    public abstract void insertEntity();
    // Abstract method for viewing all entities
    public abstract void viewAllEntities();
}
