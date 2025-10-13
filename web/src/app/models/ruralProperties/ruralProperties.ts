export interface RuralProperties {
  id: string;
  placeName: string;
  ownerName: string;
  address: string;
  size: number;
  agroChemicals: string;
  agrochemicalsLevelOrder: number;
  levelOrder: number;
  image?: string | null;
}