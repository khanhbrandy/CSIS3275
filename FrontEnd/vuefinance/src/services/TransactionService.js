import http from '../http-common';

class TransactionService {
    get(cid) {
        return http.get(`/customers/${cid}`)
    }

    createTransaction(id, data) {
        return http.post(`/customers/${id}/transactions`, data);
      }
}

export default new TransactionService();