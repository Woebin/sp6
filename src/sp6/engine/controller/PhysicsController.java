package sp6.engine.controller;

import sp6.engine.BaseObject;
import sp6.engine.SimpleVector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class PhysicsController extends Component {

    private Rectangle collisionRectangle = new Rectangle();
    private BufferedImage collisionImage;
    private SimpleVector2D vector2D = new SimpleVector2D(0, 0);


    /**
     * This is a slow implementation: O(n^2)
     * @param baseObject
     * @param baseObjects
     * @param deltaTime
     */
    @Override
    public void update(BaseObject baseObject, List<BaseObject> baseObjects, double deltaTime) {
        baseObject.physicsUpdate(this);

        for (BaseObject otherBaseObject : baseObjects) {
            if (baseObject != otherBaseObject) {
                System.out.println("other name; " + otherBaseObject.getName());
                PhysicsController otherPhysicsController = (PhysicsController) otherBaseObject.getComponent(this.getClass());
                if (isCollision(otherPhysicsController)) {
                    baseObject.physicsEnterCollision(otherBaseObject);
                }
            }
        }
    }


    public boolean boundingBoxCollision(PhysicsController physicsController) {
        return collisionRectangle.intersects(physicsController.getCollisionRectangle());
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public BufferedImage getCollisionImage() {
        return collisionImage;
    }

    public void setCollisionRectangle(Rectangle rectangle) {
        this.collisionRectangle = rectangle;
    }

    public void setCollisionImage(BufferedImage collisionImage) {
        this.collisionImage = collisionImage;
    }

    public void setVector2D(SimpleVector2D vector2D) {
        this.vector2D = vector2D;
    }

    public SimpleVector2D getVector2D() {
        return vector2D;
    }

    private boolean pixelperfectCollision(PhysicsController otherPhysicsController) {
        Rectangle otherRectangle = otherPhysicsController.getCollisionRectangle();
        Rectangle intersectRectangle = collisionRectangle.intersection(otherRectangle);
        BufferedImage otherImage = otherPhysicsController.getCollisionImage();

        if (otherImage == null || otherRectangle == null) {
            return false;
        }

        for (int x = 0; x < intersectRectangle.width; x++) {
            for (int y = 0; y < intersectRectangle.height; y++) {
                int thisX = (intersectRectangle.x - collisionRectangle.x) + x;
                int thisY = (intersectRectangle.y - collisionRectangle.y) + y;
                int otherX = (intersectRectangle.x - otherRectangle.x) + x;
                int otherY = (intersectRectangle.y - otherRectangle.y) + y;

                int alphaThis = ((collisionImage.getRGB(thisX, thisY) & 0xFF000000) >> 24);
                int alphaOther = ((otherImage.getRGB(otherX, otherY) & 0xFF000000) >> 24);

                if (alphaThis != 0 && alphaOther != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCollision(PhysicsController otherPhysicsController) {
        if (otherPhysicsController != null && boundingBoxCollision(otherPhysicsController) && pixelperfectCollision(otherPhysicsController)) {
            do {
                Point decreasedPoint = vector2D.getDecreasedVectorPoint();
                collisionRectangle.x = decreasedPoint.x;
                collisionRectangle.y = decreasedPoint.y;
            } while (pixelperfectCollision(otherPhysicsController));
            return true;
        }
        return false;
    }
}
