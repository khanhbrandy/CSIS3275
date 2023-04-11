import http from "../http-common"

class AboutUsService{

    getInstructionsByLanguage(language){
        return http.get(`/instructions/${language}`);
    }
}
export default new AboutUsService();