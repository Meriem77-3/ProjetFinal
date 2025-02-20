import { JwtPayload } from 'jwt-decode'

class CustomJwtPayload extends JwtPayload {
    constructor(role) {
        super();
        this.role = role;
    }
}

export default CustomJwtPayload;
