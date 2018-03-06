import { observable, action, toJS } from 'mobx';
import Brainstorm from './Brainstorm';
import Idea from './Idea';


export default class BrainstormStore {
    @observable brainstorms = [{
      id: 1,
      groupId: '1',
      title: 'Platform',
      status: 1,
      ideas: [{
        id: 1,
        title: 'ios app',
        voters: [],
      }, {
        id: 2,
        title: 'android app',
        voters: [],
      }, {
        id: 3,
        title: 'hybrid mobile app',
        voters: [],
      }, {
        id: 4,
        title: 'web app',
        voters: [],
      }],
    }, {
      id: 2,
      groupId: '1',
      title: 'Revenue model',
      status: 2,
      ideas: [{
        id: 1,
        title: 'Subscription',
        voters: [],
      }, {
        id: 2,
        title: 'Transaction fee',
        voters: [],
      }, {
        id: 3,
        title: 'Markup',
        voters: [],
      }, {
        id: 4,
        title: 'Freemium',
        voters: [],
      }],
    }];


    /**
     * Creating a new brainstorm session.
     * @param {string} groupId - The id of the group the brainstorm belongs to.
     * @param {string} title - The title of the brainstorm.
     */
    @action
    createBrainstorm(groupId, title) {
      const newBrainstorm = new Brainstorm(groupId, title);
      this.brainstorms.unshift(newBrainstorm);
    }

    @action
    deleteBrainstorm(brainstorm: Brainstorm) {
      const index = this.brainstorms.indexOf(brainstorm);
      if (index > -1) {
        this.brainstorms.splice(index, 1);
      }
    }

    @action
    addIdea(brainstorm, title) {
      const index = this.brainstorms.indexOf(brainstorm);
      const newIdea = new Idea(brainstorm.id, title);
      if (index > -1) {
        this.brainstorms[index].ideas.unshift(newIdea);
      }
    }

    @action
    removeIdea(brainstorm, idea) {
      const brainstormIndex = this.brainstorms.indexOf(brainstorm);
      const ideaIndex = this.brainstorms[brainstormIndex].ideas.indexOf(idea);
      if (brainstormIndex > -1 && ideaIndex > -1) {
        this.brainstorms[brainstormIndex].ideas.splice(ideaIndex, 1);
      }
    }

    /**
     * Add voter to a particular idea.
     */
    @action
    toggleVoteIdea(brainstorm, idea, voter) {
      const brainstormIndex = this.brainstorms.indexOf(brainstorm);
      const ideaIndex = this.brainstorms[brainstormIndex].ideas.indexOf(idea);
      if (brainstormIndex > -1 && ideaIndex > -1) {
        const plainVotersArr = toJS(this.brainstorms[brainstormIndex].ideas[ideaIndex].voters);
        const voterIndex = plainVotersArr.findIndex(user => user.id === voter.id);
        // if user is in voters array
        if (voterIndex > -1) {
          // remove user from voters array
          this.brainstorms[brainstormIndex].ideas[ideaIndex].voters.splice(voterIndex, 1);
          return false;
        }
        // if user is not voters array, add as voter
        this.brainstorms[brainstormIndex].ideas[ideaIndex].voters.push(voter);
        return true;
      }
      return false;
    }

    @action
    getRankedIdeas(brainstorm) {
      return brainstorm.ideas.sort((ideaA, ideaB) =>
        parseFloat(ideaB.voters.length) - parseFloat(ideaA.voters.length));
    }

    /**
     * Add voter to a particular idea.
     */
    // @action
    // voteIdea(brainstorm, idea, voter) {
    //   const brainstormIndex = this.brainstorms.indexOf(brainstorm);
    //   const ideaIndex = this.brainstorms[brainstormIndex].ideas.indexOf(idea);
    //   if (brainstormIndex > -1 && ideaIndex > -1) {
    //     // if user is not voters array, add as voter
    //     this.brainstorms[brainstormIndex].ideas[ideaIndex].voters.push(voter);
    //     console.log(this.brainstorms[brainstormIndex].ideas[ideaIndex].voters);
    //     return true;
    //   }
    //   return false;
    // }
    //
    // @action
    // unvoteIdea(brainstorm, idea, voter) {
    //   const brainstormIndex = this.brainstorms.indexOf(brainstorm);
    //   const ideaIndex = this.brainstorms[brainstormIndex].ideas.indexOf(idea);
    //   const voterIndex = this.brainstorms[brainstormIndex].ideas[ideaIndex].voters.indexOf(voter);
    //   console.log('voterIndex', voterIndex)
    //   if (brainstormIndex > -1 && ideaIndex > -1 && voterIndex > -1) {
    //     this.brainstorms[brainstormIndex].ideas[ideaIndex].voters.splice(ideaIndex, 1);
    //   }
    //   console.log(this.brainstorms[brainstormIndex].ideas[ideaIndex].voters);
    // }
}
