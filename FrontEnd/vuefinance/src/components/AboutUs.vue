<template>
    <div>
        <div>
            <h4>Instructions to use the Appllication</h4>
            <label>Select Language</label>
            <select name="languageSelect" id="languageSelect" @change="languageChange($event)">
                <option value="english">English</option>
                <option value="spanish">Spanish</option>
                <option value="french">French</option>
            </select>
        </div>
        <div class="instructionsList">
            <p class="instructions" v-for="instruction in instructions" :key="instruction.id">{{instruction}}</p>
        </div>
        
    </div>
</template>

<script>
import aboutUsService from "../services/AboutUsService";
export default {
    name:'AboutUs',
    props:[],
    components:{},
    data(){
        return {
            instructions:[]
        }
    },
    methods:{
        loadInstructions(language){
            aboutUsService.getInstructionsByLanguage(language).then(response=>{
                this.instructions = response.data.instructions;
            }).catch(e=>console.log(e))
            console.log(this.instructions);
        },
        languageChange(event){
            const language = event.target.value;
            this.loadInstructions(language);
        }
    },
    mounted(){
        this.loadInstructions('english');
    }

}
</script>

<style scoped>

h4{
    padding-top: 2%;
    padding-bottom: 2%;
}
.instructions{
        font-size: 16px;
    text-align: start;
    margin-top: 10px;
}
.instructionsList{
padding-bottom: 20px;
}
</style>