class Solution {

  public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
      int nearestX = Math.max(x1, Math.min(xCenter, x2));
      int nearestY = Math.max(y1, Math.min(yCenter, y2));
      long dx = xCenter - nearestX;
      long dy = yCenter - nearestY;
      return dx * dx + dy * dy <= (long) radius * radius;
  }
}