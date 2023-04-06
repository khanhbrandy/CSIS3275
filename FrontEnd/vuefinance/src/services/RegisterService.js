import http from "../http-common.js";

class RegisterService {
  register(data) {
    return http.post("/customers", data);
  }
}

export default new RegisterService();
