package Game;

public class CollisionDetector {
    // 충돌 여부를 검사하는 메서드
    public static boolean isCollisionWall(BlockUnion blockUnion, int leftWallX, int rightWallX, int floorY) {
        for (Block block : blockUnion.getBlocks()) {
            int blockX = block.getX();
            int blockY = block.getY();
            int blockSize = BlockRenderer.BLOCK_SIZE; // 블록 크기, BlockRenderer에서 상수로 정의되어 있다고 가정
            
            // 왼쪽 벽에 충돌한 경우
            if ((blockX*blockSize) < leftWallX) {
                return true;
            }

            // 오른쪽 벽에 충돌한 경우
            if ((blockX*(blockSize+1)) > rightWallX) {
                return true;
            }

            // 바닥에 충돌한 경우
            if ((blockY*(blockSize+1)) > floorY) {
                return true;
            }
        }

        // 모든 블록이 경계 내에 있다면 충돌하지 않은 것으로 간주
        return false;
    }
    
    public static boolean isCollisionBlockU(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 1 &&
                    block.getX() + 1 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 2 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
    
    public static boolean isCollisionBlockL(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 1 &&
                    block.getX() + 2 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 1 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
    
    public static boolean isCollisionBlockR(BlockUnion blockUnion, BlockUnion deadBlocks) {
        for (Block block : blockUnion.getBlocks()) {
            for (Block otherBlock : deadBlocks.getBlocks()) {
                if (block.getX() < otherBlock.getX() + 2 &&
                    block.getX() + 1 > otherBlock.getX() &&
                    block.getY() < otherBlock.getY() + 1 &&
                    block.getY() + 1 > otherBlock.getY()) {
                    return true; // 충돌 감지
                }
            }
        }
        return false; // 충돌 없음
    }
}