const getGroupDetails = (groupId) => {
  const fakeGroupDetails = {
    id: '111',
    name: 'PM Champ',
    description: 'PM private message project management pyjamas man.',
    img: 'group.png',
  };
  return fakeGroupDetails;
};

const getUserGroups = (userId) => {
  const fakeUserGroups = [
    {
      id: '1',
      name: 'PM Champ',
    },
    {
      id: '2',
      name: 'Frontend Pro',
    },
    {
      id: '3',
      name: 'Capstone Rocks',
    },
  ];
  return fakeUserGroups;
};

export { getGroupDetails, getUserGroups };
