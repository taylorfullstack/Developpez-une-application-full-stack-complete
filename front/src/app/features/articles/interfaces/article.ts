export interface Article {
  id: number;
  title: string;
  content: string;
  authorName: string;
  authorId: number;
  themeTitle: string;
  themeId: number;
  comments: Comment[];
  created_at: Date;
  updated_at: Date;
}

export interface Comment {
  id: number;
  content: string;
  authorName: string;
  authorId: number;
  articleId: number;
  created_at: Date;
  updated_at: Date;
}
