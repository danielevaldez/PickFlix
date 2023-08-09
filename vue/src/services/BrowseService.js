import axios from 'axios';

export default {

    getMovies(profileID){
        return axios.get('/browse/' + profileID)
    }
    
}